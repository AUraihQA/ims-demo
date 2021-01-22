package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.OrderItems;

public class OrderItemsDaoMysql implements Dao<OrderItems> {
	public static final Logger LOGGER = Logger.getLogger(OrderItemsDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public OrderItemsDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://35.242.183.112:3306/ims";
		this.username = username;
		this.password = password;
	}

	public OrderItemsDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}

	OrderItems orderItemsFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long itemID = resultSet.getLong("itemID");
		Long orderID = resultSet.getLong("orderID");
		Double Price = resultSet.getDouble("Price");
		Integer quantity = resultSet.getInt("quantity");
		String ItemName = resultSet.getString("item_name");
		return new OrderItems(id, itemID, orderID, quantity, Price, ItemName);
	}

	@Override
	public List<OrderItems> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"select oi.id, oi.orderID, oi.itemID, oi.quantity, items.Price, items.item_name from order_items oi join items on items.id=oi.itemID;");) {
			ArrayList<OrderItems> orderitems = new ArrayList<>();
			while (resultSet.next()) {
				orderitems.add(orderItemsFromResultSet(resultSet));
			}
			return orderitems;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();

	}

	public OrderItems readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"select oi.id, oi.orderID, oi.itemID, oi.quantity, items.Price, items.item_name from order_items oi join items on items.id=oi.itemID ORDER BY id DESC LIMIT 1;");) {
			resultSet.next();
			return orderItemsFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public OrderItems create(OrderItems orderItems) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate(
					"insert into order_items(orderID, itemID, quantity) values('" + orderItems.getOrderID() + "','"
							+ orderItems.getItemID() + "','" + orderItems.getQuantity() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public OrderItems readOrderItems(Long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"select order_items.id, order_items.orderID, order_items.itemID, order_items.quantity, items.Price, items.item_name from order_items join items on items.id=order_items.itemID WHERE order_items.id= 3"
								+ id)) {
			resultSet.next();
			return orderItemsFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public OrderItems update(OrderItems orderItems) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement()) {
			statement.executeUpdate("update order_items set orderID ='" + orderItems.getOrderID() + "', itemID ='"
					+ orderItems.getItemID() + "', quantity ='" + orderItems.getQuantity() + "' where id ="
					+ orderItems.getId());
			return readOrderItems(orderItems.getId());
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
			statement.executeUpdate("delete from order_items where id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());

		}

	}
}
