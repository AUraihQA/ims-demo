package Dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.Ims;
import com.qa.ims.persistence.dao.OrderItemsDaoMysql;
import com.qa.ims.persistence.domain.OrderItems;

public class OrderItemsDaoMysqlTest {

	public static final Logger LOGGER = Logger.getLogger(OrderItemsDaoMysql.class);

	private static String jdbcConnectionUrl = "jdbc:mysql://localhost:3306/ims_test";
	private static String username = "root";
	private static String password = "root";

	@BeforeClass
	public static void init() {
		Ims ims = new Ims();
		ims.init(jdbcConnectionUrl, username, password, "src/test/resources/sql-schema.sql");
	}

	@Before
	public void setUp() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete * from ims_test.order_items;");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	@Before
	public void setUpCustomer() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO customers(id, first_name, surname) VALUES (1, 'Adi', 'Uraih');");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	@Before
	public void setUpItem() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO items(id, item_name, Price) VALUES (1, 'PS4', 300);");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	@Before
	public void setUpOrder() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate(
					"INSERT INTO orders(id, order_address, order_date, customerid) VALUES (1, '123 Road', '20th January 2021', 1);");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	@Test
	public void createTest() {
		OrderItemsDaoMysql orderitemsDaoMysql = new OrderItemsDaoMysql(jdbcConnectionUrl, username, password);
		Long OrderID = 1L;
		Integer quantity = 1;
		Double Price = 300.0;
		String ItemName = "PS4";
		Long itemID = 1L;
		OrderItems orderitems = new OrderItems(itemID, OrderID, quantity, Price, ItemName);
		OrderItems savedOrderItems = new OrderItems(null, itemID, OrderID, quantity, Price, ItemName);
		orderitems = orderitemsDaoMysql.create(orderitems);
		orderitems.setId(null);
		assertEquals(savedOrderItems, orderitems);

	}

	@Test
	public void readTest() {
		OrderItemsDaoMysql orderitemsDaoMysql = new OrderItemsDaoMysql(jdbcConnectionUrl, username, password);
		Long OrderID = 1L;
		Integer quantity = 1;
		Double Price = 300.0;
		String ItemName = "PS4";
		Long itemID = 1L;
		OrderItems orderitems = new OrderItems(itemID, OrderID, quantity, Price, ItemName);
		OrderItems savedOrderItems = new OrderItems(1L, itemID, OrderID, quantity, Price, ItemName);
		orderitems = orderitemsDaoMysql.create(orderitems);
		orderitemsDaoMysql.readOrderItems(1L);
		assertEquals(savedOrderItems, orderitems);
	}

	@Test
	public void updateTest() {
		OrderItemsDaoMysql orderitemsDaoMysql = new OrderItemsDaoMysql(jdbcConnectionUrl, username, password);
		Long OrderID = 1L;
		Integer quantity = 1;
		Double Price = 300.0;
		String ItemName = "PS4";
		Long itemID = 1L;
		OrderItems orderitems = new OrderItems(itemID, OrderID, quantity, Price, ItemName);
		OrderItems savedOrderItems = new OrderItems(null, itemID, OrderID, quantity, Price, ItemName);
		orderitems = orderitemsDaoMysql.create(orderitems);
		orderitemsDaoMysql.update(null);
		orderitems.setId(null);
		assertEquals(savedOrderItems, orderitems);
	}

	@Test
	public void readAllTest() {
		OrderItemsDaoMysql orderitemsDaoMysql = new OrderItemsDaoMysql(jdbcConnectionUrl, username, password);
		Long OrderID = 1L;
		Integer quantity = 1;
		Double Price = 300.0;
		String ItemName = "PS4";
		Long itemID = 1L;
		OrderItems orderitems = new OrderItems(itemID, OrderID, quantity, Price, ItemName);
		OrderItems savedOrderItems = new OrderItems(null, itemID, OrderID, quantity, Price, ItemName);
		List<OrderItems> orderitem = new ArrayList<>();
		orderitem.add(orderitems);
		orderitemsDaoMysql.readAll();
		assertEquals(savedOrderItems, orderitems);

	}

	@Test
	public void deleteTest() {
		OrderItemsDaoMysql ordersDaoMysql = new OrderItemsDaoMysql(jdbcConnectionUrl, username, password);
		Long id = 1L;
		ordersDaoMysql.delete(id);
	}
}
