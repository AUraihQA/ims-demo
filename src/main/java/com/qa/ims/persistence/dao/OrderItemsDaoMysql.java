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
		this.jdbcConnectionUrl = "jdbc:mysql://localhost:3306/ims";
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
		Long orderID = resultSet.getLong("orderID");
		Long itemsID = resultSet.getLong("itemsID");
		Double Price = resultSet.getDouble("Price");
		return new OrderItems(id, orderID, itemsID, Price);
	}

	@Override
	public List<OrderItems> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"select oi.id, oi.orderID, oi.itemID, oi.quantity, items.Price from order_items oi join items on items.id=oi.itemID;");) {
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

	@Override
	public OrderItems create(OrderItems t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItems update(OrderItems t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

}
