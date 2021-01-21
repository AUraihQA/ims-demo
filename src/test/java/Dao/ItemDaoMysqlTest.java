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
import com.qa.ims.persistence.dao.ItemsDaoMysql;
import com.qa.ims.persistence.domain.Items;

public class ItemDaoMysqlTest {
	public static final Logger LOGGER = Logger.getLogger(ItemsDaoMysql.class);

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
			statement.executeUpdate("delete from ims_test.items");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	@Test
	public void createTest() {
		ItemsDaoMysql itemsDaoMysql = new ItemsDaoMysql(jdbcConnectionUrl, username, password);
		String ItemName = "item1";
		Double Price = 300.00;
		Items items = new Items(ItemName, Price);
		Items savedItem = new Items(null, ItemName, Price);
		items = itemsDaoMysql.create(items);
		items.setId(null);
		assertEquals(savedItem, items);
	}

	@Test
	public void readTest() {
		ItemsDaoMysql itemsDaoMysql = new ItemsDaoMysql(jdbcConnectionUrl, username, password);
		String ItemName = "item1";
		Double Price = 300.00;
		Items items = new Items(null, ItemName, Price);
		Items savedItem = new Items(null, ItemName, Price);
		items = itemsDaoMysql.create(items);
		items.setId(null);
		itemsDaoMysql.readItems(null);
		assertEquals(savedItem, items);
	}

	@Test
	public void updateTest() {
		ItemsDaoMysql itemsDaoMysql = new ItemsDaoMysql(jdbcConnectionUrl, username, password);
		String ItemName = "item1";
		Double Price = 300.00;
		Items items = new Items(null, ItemName, Price);
		Items savedItem = new Items(null, ItemName, Price);
		items = itemsDaoMysql.create(items);
		itemsDaoMysql.update(items);
		items.setId(null);
		assertEquals(savedItem, items);

	}

	@Test
	public void readAllTest() {
		ItemsDaoMysql itemsDaoMysql = new ItemsDaoMysql(jdbcConnectionUrl, username, password);
		String ItemName = "item1";
		Double Price = 300.0;
		Items item = new Items(1L, ItemName, Price);
		Items savedItem = new Items(1L, ItemName, Price);
		List<Items> items = new ArrayList<>();
		items.add(item);
		itemsDaoMysql.readAll();
		assertEquals(savedItem, item);

	}

	@Test
	public void deleteTest() {
		ItemsDaoMysql itemsDaoMysql = new ItemsDaoMysql(jdbcConnectionUrl, username, password);
		Long id = 1L;
		itemsDaoMysql.delete(id);
	}
}
