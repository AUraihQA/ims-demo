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
import com.qa.ims.persistence.dao.OrdersDaoMysql;
import com.qa.ims.persistence.domain.Orders;

public class OrderDaoMysqlTest {

	public static final Logger LOGGER = Logger.getLogger(OrdersDaoMysql.class);

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
			statement.executeUpdate("delete * from ims_test.orders;");
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

	@Before
	public void setUpOrderItems() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO order_items(id, orderID, itemID, quantity) VALUES (1, 1, 1, 1);");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	@Test
	public void createTest() {
		OrdersDaoMysql ordersDaoMysql = new OrdersDaoMysql(jdbcConnectionUrl, username, password);
		String OrderAddress = "123 Road";
		String OrderDate = "20th January 2021";
		Long CustomerID = 1L;
		Integer quantity = 1;
		Double Price = 300.0;
		String ItemName = "PS4";
		Long itemID = 1L;
		Orders orders = new Orders(OrderAddress, OrderDate, CustomerID, itemID, ItemName, Price, quantity);
		Orders savedOrder = new Orders(1L, OrderAddress, OrderDate, CustomerID, itemID, ItemName, Price, quantity);
		orders = ordersDaoMysql.create(orders);
		assertEquals(savedOrder, orders);

	}

	@Test
	public void readTest() {
		OrdersDaoMysql ordersDaoMysql = new OrdersDaoMysql(jdbcConnectionUrl, username, password);
		String OrderAddress = "123 Road";
		String OrderDate = "20th January 2021";
		Long CustomerID = 1L;
		Integer quantity = 1;
		Double Price = 300.0;
		String ItemName = "PS4";
		Long itemID = 1L;
		Orders orders = new Orders(OrderAddress, OrderDate, CustomerID, itemID, ItemName, Price, quantity);
		Orders savedOrder = new Orders(1L, OrderAddress, OrderDate, CustomerID, itemID, ItemName, Price, quantity);
		orders = ordersDaoMysql.create(orders);
//		orders.setId(null);
		ordersDaoMysql.readOrders(2L);
		assertEquals(savedOrder, orders);
	}

	@Test
	public void updateTest() {
		OrdersDaoMysql ordersDaoMysql = new OrdersDaoMysql(jdbcConnectionUrl, username, password);
		String OrderAddress = "123 Road";
		String OrderDate = "20th January 2021";
		Long CustomerID = 1L;
		Integer quantity = 1;
		Double Price = 300.0;
		String ItemName = "PS4";
		Long itemID = 1L;
		Orders orders = new Orders(OrderAddress, OrderDate, CustomerID, itemID, ItemName, Price, quantity);
		Orders savedOrder = new Orders(1L, OrderAddress, OrderDate, CustomerID, itemID, ItemName, Price, quantity);
		orders = ordersDaoMysql.create(orders);
		ordersDaoMysql.update(orders);
		assertEquals(savedOrder, orders);

	}

	@Test
	public void readAllTest() {
		OrdersDaoMysql ordersDaoMysql = new OrdersDaoMysql(jdbcConnectionUrl, username, password);
		String OrderAddress = "123 road";
		String OrderDate = "19th January 2020";
		Long CustomerID = 1L;
		Orders order = new Orders(null, OrderAddress, OrderDate, CustomerID);
		Orders savedOrder = new Orders(null, OrderAddress, OrderDate, CustomerID);
		List<Orders> orders = new ArrayList<>();
		orders.add(order);
		ordersDaoMysql.readAll();
		assertEquals(savedOrder, order);

	}

	@Test
	public void deleteTest() {
		OrdersDaoMysql ordersDaoMysql = new OrdersDaoMysql(jdbcConnectionUrl, username, password);
		Long id = 1L;
		ordersDaoMysql.delete(id);
	}
}
