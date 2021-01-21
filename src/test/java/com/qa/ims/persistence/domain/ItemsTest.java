package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ItemsTest {
	private Items item;
	private Items other;

	@Before
	public void setUp() {
		item = new Items(1L, "item1", 300.00);
		other = new Items(1L, "item1", 300.00);
	}

	@Test
	public void settersTest() {
		assertNotNull(item.getId());
		assertNotNull(item.getItemName());
		assertNotNull(item.getPrice());

		item.setId(null);
		assertNull(item.getId());
		item.setItemName(null);
		assertNull(item.getItemName());
		item.setPrice(null);
		assertNull(item.getPrice());

	}

	@Test
	public void equalsWithNull() {
		assertFalse(item.equals(null));
	}

	@Test
	public void equalsWithDifferentObject() {
		assertFalse(item.equals(new Object()));
	}

	@Test
	public void createItemWithId() {
		assertEquals(1L, item.getId(), 0);
		assertEquals("item1", item.getItemName());
		assertEquals(300.00, item.getPrice(), 0);
	}

	@Test
	public void checkEquality() {
		assertTrue(item.equals(item));
	}

	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(item.equals(other));
	}

	@Test
	public void itemNameNullButOtherNameNotNull() {
		item.setItemName(null);
		assertFalse(item.equals(other));
	}

	@Test
	public void itemNamesNotEqual() {
		other.setItemName("item2");
		assertFalse(item.equals(other));
	}

	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		item.setItemName(null);
		other.setItemName(null);
		assertTrue(item.equals(other));
	}

	@Test
	public void nullId() {
		item.setId(null);
		assertFalse(item.equals(other));
	}

	@Test
	public void nullIdOnBoth() {
		item.setId(null);
		other.setId(null);
		assertTrue(item.equals(other));
	}

	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(item.equals(other));
	}

	@Test
	public void nullPrice() {
		item.setPrice(null);
		assertFalse(item.equals(other));
	}

	@Test
	public void nullPriceOnBoth() {
		item.setPrice(null);
		other.setPrice(null);
		assertTrue(item.equals(other));
	}

	@Test
	public void otherPriceDifferent() {
		other.setPrice(500.0);
		assertFalse(item.equals(other));
	}

	@Test
	public void constructorWithoutId() {
		Items item = new Items("item1", 500.0);
		assertNull(item.getId());
		assertNotNull(item.getItemName());
		assertNotNull(item.getPrice());
	}

	@Test
	public void hashCodeTest() {
		assertEquals(item.hashCode(), other.hashCode());
	}

	@Test
	public void hashCodeTestWithNull() {
		Items item = new Items(null, null);
		Items other = new Items(null, null);
		assertEquals(item.hashCode(), other.hashCode());
	}

	@Test
	public void toStringTest() {
		String toString = "id: 1 Item Name: item1 Price: 300.0";
		assertEquals(toString, item.toString());
	}
}
