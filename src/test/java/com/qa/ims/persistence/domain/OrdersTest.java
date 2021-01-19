package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OrdersTest {
	private Orders orders;
	private Orders other;
	private Orders foreign;
	private Orders otherForeign;

	@Before
	public void setUp() {
		orders = new Orders(1L, "123 road", "19th January 2020", 1L);
		other = new Orders(1L, "123 road", "19th January 2020", 1L);
		foreign = new Orders(1L, "123 road", "19th January 2020", 1L, 1L, "item1", 300.00, 2);
		otherForeign = new Orders(1L, "123 road", "19th January 2020", 1L, 1L, "item1", 300.00, 2);

	}

	@Test
	public void settersTest() {
		assertNotNull(orders.getId());
		assertNotNull(orders.getOrderAddress());
		assertNotNull(orders.getOrderDate());
		assertNotNull(orders.getCustomerID());
		assertNotNull(foreign.getItemID());
		assertNotNull(foreign.getItemName());
		assertNotNull(foreign.getPrice());
		assertNotNull(foreign.getQuantity());

		orders.setId(null);
		assertNull(orders.getId());
		orders.setOrderAddress(null);
		assertNull(orders.getOrderAddress());
		orders.setOrderDate(null);
		assertNull(orders.getOrderDate());
		orders.setCustomerID(null);
		assertNull(orders.getCustomerID());
		foreign.setItemID(null);
		assertNull(foreign.getItemID());
		foreign.setItemName(null);
		assertNull(foreign.getItemName());
		foreign.setPrice(null);
		assertNull(foreign.getPrice());
		foreign.setQuantity(0);
		assertNotNull(foreign.getQuantity());

	}

	@Test
	public void equalsWithNull() {
		assertFalse(orders.equals(null));
		assertFalse(foreign.equals(null));
	}

	@Test
	public void equalsWithDifferentObject() {
		assertFalse(orders.equals(new Object()));
		assertFalse(foreign.equals(new Object()));
	}

	@Test
	public void createItemWithId() {
		assertEquals(1L, orders.getId(), 0);
		assertEquals("123 road", orders.getOrderAddress());
		assertEquals("19th January 2020", orders.getOrderDate());
		assertEquals(1L, orders.getCustomerID(), 0);
		assertEquals(1L, foreign.getId(), 0);
		assertEquals("123 road", foreign.getOrderAddress());
		assertEquals("19th January 2020", foreign.getOrderDate());
		assertEquals(1L, foreign.getCustomerID(), 0);
		assertEquals(1L, foreign.getItemID(), 0);
		assertEquals("item1", foreign.getItemName());
		assertEquals(300.00, foreign.getPrice(), 0);
		assertEquals(2, foreign.getQuantity());

	}

	@Test
	public void checkEquality() {
		assertTrue(orders.equals(orders));
		assertTrue(foreign.equals(foreign));

	}

	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(orders.equals(other));
		assertTrue(foreign.equals(otherForeign));
	}

	@Test
	public void orderAddressNullButOtherNameNotNull() {
		orders.setOrderAddress(null);
		assertFalse(orders.equals(other));
		foreign.setOrderAddress(null);
		assertFalse(foreign.equals(otherForeign));
	}

	@Test
	public void orderAddressNotEqual() {
		other.setOrderAddress("123 street");
		assertFalse(orders.equals(other));
		otherForeign.setOrderAddress("123 street");
		assertFalse(foreign.equals(otherForeign));
	}

	@Test
	public void orderDateNullButOtherNameNotNull() {
		orders.setOrderDate(null);
		assertFalse(orders.equals(other));
		foreign.setOrderDate(null);
		assertFalse(foreign.equals(otherForeign));
	}

	@Test
	public void orderDateNotEqual() {
		other.setOrderDate("18th January 2020");
		assertFalse(orders.equals(other));
		otherForeign.setOrderDate("18th January 2020");
		assertFalse(foreign.equals(otherForeign));
	}

	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		orders.setOrderAddress(null);
		other.setOrderAddress(null);
		orders.setOrderDate(null);
		other.setOrderDate(null);
		assertTrue(orders.equals(other));
		foreign.setOrderAddress(null);
		otherForeign.setOrderAddress(null);
		assertTrue(foreign.equals(otherForeign));
	}

	@Test
	public void nullId() {
		orders.setId(null);
		assertFalse(orders.equals(other));
		foreign.setId(null);
		assertFalse(foreign.equals(otherForeign));
	}

	@Test
	public void nullIdOnBoth() {
		orders.setId(null);
		other.setId(null);
		assertTrue(orders.equals(other));
		foreign.setId(null);
		otherForeign.setId(null);
		assertTrue(foreign.equals(otherForeign));
	}

	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(orders.equals(other));
		otherForeign.setId(2L);
		assertFalse(foreign.equals(otherForeign));
	}

	@Test
	public void nullCustomerID() {
		orders.setCustomerID(null);
		assertFalse(orders.equals(other));
		foreign.setCustomerID(null);
		assertFalse(foreign.equals(otherForeign));
	}

	@Test
	public void nullCustomerIDOnBoth() {
		orders.setCustomerID(null);
		other.setCustomerID(null);
		assertTrue(orders.equals(other));
		foreign.setCustomerID(null);
		otherForeign.setCustomerID(null);
		assertTrue(foreign.equals(otherForeign));
	}

	@Test
	public void otherCustomerIDDifferent() {
		other.setCustomerID(2L);
		assertFalse(orders.equals(other));
		otherForeign.setCustomerID(2L);
		assertFalse(foreign.equals(otherForeign));
	}

	@Test
	public void nullItemID() {
		foreign.setItemID(null);
		assertFalse(foreign.equals(otherForeign));
	}

	@Test
	public void nullItemIDOnBoth() {
		foreign.setItemID(null);
		otherForeign.setItemID(null);
		assertTrue(foreign.equals(otherForeign));
	}

	@Test
	public void otherItemIDDifferent() {
		otherForeign.setItemID(2L);
		assertFalse(foreign.equals(otherForeign));
	}

	@Test
	public void nullItemName() {
		foreign.setItemName(null);
		assertFalse(foreign.equals(otherForeign));
	}

	@Test
	public void nullItemNameOnBoth() {
		foreign.setItemName(null);
		otherForeign.setItemName(null);
		assertTrue(foreign.equals(otherForeign));
	}

	@Test
	public void otherItemNameDifferent() {
		otherForeign.setItemName("item2");
		assertFalse(foreign.equals(otherForeign));
	}

	@Test
	public void nullPrice() {
		foreign.setPrice(null);
		assertFalse(foreign.equals(otherForeign));
	}

	@Test
	public void nullPriceOnBoth() {
		foreign.setPrice(null);
		otherForeign.setPrice(null);
		assertTrue(foreign.equals(otherForeign));
	}

	@Test
	public void otherPriceDifferent() {
		otherForeign.setPrice(500.00);
		assertFalse(foreign.equals(otherForeign));
	}

	@Test
	public void nullQuantity() {
		foreign.setQuantity(0);
		assertFalse(foreign.equals(otherForeign));
	}

	@Test
	public void nullQuantityOnBoth() {
		foreign.setQuantity(0);
		otherForeign.setQuantity(0);
		assertTrue(foreign.equals(otherForeign));
	}

	@Test
	public void otherQuantityDifferent() {
		otherForeign.setQuantity(1);
		assertFalse(foreign.equals(otherForeign));
	}

	@Test
	public void constructorWithoutId() {
		Orders orders = new Orders("123 road", "19th January 2020", 1L);
		assertNull(orders.getId());
		assertNotNull(orders.getOrderAddress());
		assertNotNull(orders.getOrderDate());
		assertNotNull(orders.getCustomerID());
		Orders foreign = new Orders("123 road", "19th January 2020", 1L, 1L, "item1", 300.00, 2);
		assertNull(foreign.getId());
		assertNotNull(foreign.getOrderAddress());
		assertNotNull(foreign.getOrderDate());
		assertNotNull(foreign.getCustomerID());
		assertNotNull(foreign.getItemID());
		assertNotNull(foreign.getItemName());
		assertNotNull(foreign.getPrice());
		assertNotNull(foreign.getQuantity());
	}

	@Test
	public void hashCodeTest() {
		assertEquals(foreign.hashCode(), otherForeign.hashCode());
	}

	@Test
	public void hashCodeTestWithNull() {
		Orders foreign = new Orders(null, null, null);
		Orders otherForeign = new Orders(null, null, null);
		assertEquals(foreign.hashCode(), otherForeign.hashCode());
	}

	@Test
	public void toStringTest() {
		String toString = "id:1 OrderAddress:123 road OrderDate:19th January 2020 CustomerID:1 ItemID:1 ItemName:item1 Price:300.0 Quantity:2";
		assertEquals(toString, foreign.toString());
	}

}
