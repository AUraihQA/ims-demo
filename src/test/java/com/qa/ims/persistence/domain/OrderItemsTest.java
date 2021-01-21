package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OrderItemsTest {
	private OrderItems orderItems;
	private OrderItems other;
	private OrderItems foreign;
	private OrderItems otherForeign;

	@Before
	public void setUp() {
		orderItems = new OrderItems(1L, 1L, 1L, 1);
		other = new OrderItems(1L, 1L, 1L, 1);
		foreign = new OrderItems(1L, 1L, 1L, 1, 300.00, "item1");
		otherForeign = new OrderItems(1L, 1L, 1L, 1, 300.00, "item1");

	}

	@Test
	public void settersTest() {
		assertNotNull(orderItems.getId());
		assertNotNull(orderItems.getOrderID());
		assertNotNull(orderItems.getItemID());
		assertNotNull(orderItems.getQuantity());
		assertNotNull(foreign.getPrice());
		assertNotNull(foreign.getItemName());

		orderItems.setId(null);
		assertNull(orderItems.getId());
		orderItems.setOrderID(null);
		assertNull(orderItems.getOrderID());
		orderItems.setItemID(null);
		assertNull(orderItems.getItemID());
		orderItems.setQuantity(0);
		assertNotNull(orderItems.getQuantity());
		foreign.setPrice(null);
		assertNull(foreign.getPrice());
		foreign.setItemName(null);
		assertNull(foreign.getItemName());

	}

	@Test
	public void equalsWithNull() {
		assertFalse(orderItems.equals(null));
	}

	@Test
	public void equalsWithDifferentObject() {
		assertFalse(orderItems.equals(new Object()));
	}

	@Test
	public void createItemWithId() {
		assertEquals(1L, orderItems.getId(), 0);
		assertEquals(1L, orderItems.getOrderID(), 0);
		assertEquals(1L, orderItems.getItemID(), 0);
		assertEquals(1, orderItems.getQuantity());
	}

	@Test
	public void checkEquality() {
		assertTrue(orderItems.equals(orderItems));

	}

	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(orderItems.equals(other));
	}

	@Test
	public void orderIDNullButOtherNameNotNull() {
		orderItems.setOrderID(null);
		assertFalse(orderItems.equals(other));
	}

	@Test
	public void orderIDNotEqual() {
		other.setOrderID(2L);
		assertFalse(orderItems.equals(other));
	}

	@Test
	public void itemIDNullButOtherNameNotNull() {
		orderItems.setItemID(null);
		assertFalse(orderItems.equals(other));
	}

	@Test
	public void itemIDNotEqual() {
		other.setItemID(2L);
		assertFalse(orderItems.equals(other));
	}

	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		orderItems.setOrderID(null);
		other.setOrderID(null);
		orderItems.setItemID(null);
		other.setItemID(null);
		assertTrue(orderItems.equals(other));
	}

	@Test
	public void nullId() {
		orderItems.setId(null);
		assertFalse(orderItems.equals(other));
	}

	@Test
	public void nullIdOnBoth() {
		orderItems.setId(null);
		other.setId(null);
		assertTrue(orderItems.equals(other));
	}

	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(orderItems.equals(other));
	}

	@Test
	public void nullQuantity() {
		orderItems.setQuantity(0);
		assertFalse(orderItems.equals(other));
	}

	@Test
	public void nullQuantityOnBoth() {
		orderItems.setQuantity(0);
		other.setQuantity(0);
		assertTrue(orderItems.equals(other));
	}

	@Test
	public void otherQuantityDifferent() {
		other.setQuantity(3);
		assertFalse(orderItems.equals(other));
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
	public void constructorWithoutId() {
		OrderItems orderItems = new OrderItems(1L, 1L, 1);
		assertNull(orderItems.getId());
		assertNotNull(orderItems.getOrderID());
		assertNotNull(orderItems.getItemID());
		assertNotNull(orderItems.getQuantity());
		OrderItems foreign = new OrderItems(1L, 1L, 1, 300.00, "item1");
		assertNotNull(foreign.getOrderID());
		assertNotNull(foreign.getItemID());
		assertNotNull(foreign.getQuantity());
		assertNotNull(foreign.getPrice());
		assertNotNull(foreign.getItemName());
	}

	@Test
	public void hashCodeTest() {
		assertEquals(foreign.hashCode(), otherForeign.hashCode());
	}

	@Test
	public void hashCodeTestWithNull() {
		OrderItems foreign = new OrderItems(null, null, null, 0, null, null);
		OrderItems otherForeign = new OrderItems(null, null, null, 0, null, null);
		assertEquals(foreign.hashCode(), otherForeign.hashCode());
	}

	@Test
	public void toStringTest() {
		String toString = "id:1 ItemID:1 OrderID:1 ItemName:item1 Price:300.0 Quantity:1";
		assertEquals(toString, foreign.toString());
	}

}
