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
		orderItems.add(new OrderItems(1L, 1L, 1));
		orderItems.add(new OrderItems(2L, 2L, 1));
		orderItems.add(new OrderItems(3L, 3L, 3));
		Mockito.when(orderItemsServices.readAll()).thenReturn(orderItems);
		assertEquals(orderItems, orderItemsController.readAll());
	}

	@Test
	public void createTest() {
		Long orderID = 1L;
		Integer inputForNextBuy = 1;
		String inputForNextBuyN = "0";
		Long itemID = 1L;
		Integer quantity = 1;
		Double price = 300.00;
		String ItemName = "item1";
		Mockito.doReturn(orderID, itemID).when(orderItemsController).getInputL();
		Mockito.doReturn(quantity, inputForNextBuy).when(orderItemsController).getInputI();
		Mockito.doReturn(inputForNextBuyN).when(orderItemsController).getInput();
		OrderItems Orderitems = new OrderItems(itemID, orderID, quantity);
		OrderItems savedOrderItems = new OrderItems(1L, 1L, 1L, quantity, price, ItemName);
		Mockito.when(orderItemsServices.create(Orderitems)).thenReturn(savedOrderItems);
		assertEquals(savedOrderItems, orderItemsController.create());
	}

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
