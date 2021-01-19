package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.services.OrderItemsServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderItemsControllerTest {
	@Mock
	private OrderItemsServices orderItemsServices;

	@Spy
	@InjectMocks
	private OrderItemsController orderItemsController;

	@Test
	public void readAllTest() {
		OrderItemsController orderItemsController = new OrderItemsController(orderItemsServices);
		List<OrderItems> orderItems = new ArrayList<>();
		orderItems.add(new OrderItems(1L, 1L, 2));
		orderItems.add(new OrderItems(2L, 2L, 1));
		orderItems.add(new OrderItems(3L, 3L, 3));
		Mockito.when(orderItemsServices.readAll()).thenReturn(orderItems);
		assertEquals(orderItems, orderItemsController.readAll());
	}

	@Test
	public void createTest() {
		// Double price = Orderitems.getPrice();
		Long orderID = 1L;
		Integer inputForNextBuy = 1;
		Long itemID = 1L;
		Integer quantity = 2;
		// Double price = 300.00;
		Double Amount = 300.00;
		String ItemName = "item1";
		Mockito.doReturn(orderID, itemID).when(orderItemsController).getInputL();
		Mockito.doReturn(quantity, inputForNextBuy).when(orderItemsController).getInputI();
//		Mockito.doReturn(price, Amount).when(orderItemsController).getInputD();
		Mockito.doReturn(ItemName).when(orderItemsController).getInput();
		OrderItems Orderitems = new OrderItems(itemID, orderID, quantity);
		Double price = Orderitems.getPrice();
		Mockito.doReturn(price, Amount).when(orderItemsController).getInputD();
		Mockito.when(orderItemsServices.create(Orderitems)).thenReturn(Orderitems);
		assertEquals(Orderitems, orderItemsController.create());
	}
//		Long order_address = "123 road";
//		String order_date = "19th January 2020";
//		Long customerid = 1L;
//		Mockito.doReturn(order_address, order_date).when(ordersController).getInput();
//		Mockito.doReturn(customerid).when(ordersController).getInputL();
//		Orders orders = new Orders(order_address, order_date, customerid);
//		Orders savedOrder = new Orders(1L, "123 road", "19th January 2020", 1L);
//		Mockito.when(ordersServices.create(orders)).thenReturn(savedOrder);
//		assertEquals(savedOrder, ordersController.create());
//	}

	@Test
	public void updateTest() {
		String id = "1";
		Long orderID = 1L;
		Long itemID = 1L;
		Integer quantity = 2;
		Mockito.doReturn(id).when(orderItemsController).getInput();
		Mockito.doReturn(orderID, itemID).when(orderItemsController).getInputL();
		Mockito.doReturn(quantity).when(orderItemsController).getInputI();
		OrderItems orderItems = new OrderItems(1L, orderID, itemID, quantity);
		Mockito.when(orderItemsServices.update(orderItems)).thenReturn(orderItems);
		assertEquals(orderItems, orderItemsController.update());
	}

	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(orderItemsController).getInput();
		orderItemsController.delete();
		Mockito.verify(orderItemsServices, Mockito.times(1)).delete(1L);
	}

}
