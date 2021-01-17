package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Orders;

public class OrdersDaoMysql implements Dao<Orders> {
	public static final Logger LOGGER = Logger.getLogger(OrdersDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public OrdersDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://localhost:3306/ims";
		this.username = username;
		this.password = password;
	}

	public OrdersDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;

	}

	Orders ordersFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String OrderAddress = resultSet.getString("OrderAddress");
		String OrderDate = resultSet.getString("OrderDate");
		Long customerID = resultSet.getLong("customerID");
		return new Orders(id, OrderAddress, OrderDate, customerID);
	}

	@Override
	public List<Orders> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from Orders");) {
			ArrayList<Orders> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(ordersFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Orders readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return ordersFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Orders create(Orders orders) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("insert into orders(order_address, order_date, customerid) values('"
					+ orders.getOrderAddress() + "','" + orders.getOrderDate() + "','" + orders.getCustomerID() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Orders readOrders(Long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT orders.id, orders.order_address, orders.customerid, order_items.quantity, items.item_name from order_items join orders on order_items.orderid=orders.id join items on items.id=order_items.itemID WHERE orders.id="
								+ id)) {
			resultSet.next();
			return ordersFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Orders update(Orders Orders) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement()) {
			statement.executeUpdate("update orders set order_address ='" + Orders.getOrderAddress() + "', order_date ='"
					+ Orders.getOrderDate() + "', customerid ='" + Orders.getCustomerID() + "' where id ="
					+ Orders.getId());
			return readOrders(Orders.getId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public void delete(long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orders where id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());

		}
	}

}
