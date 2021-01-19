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

import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.services.OrdersServices;

@RunWith(MockitoJUnitRunner.class)
public class OrdersControllerTest {
	@Mock
	private OrdersServices ordersServices;

	@Spy
	@InjectMocks
	private OrdersController ordersController;

	@Test
	public void readAllTest() {
		OrdersController ordersController = new OrdersController(ordersServices);
		List<Orders> orders = new ArrayList<>();
		orders.add(new Orders("123 road", "19th January 2020", 1L));
		orders.add(new Orders("123 street", "18th January 2020", 2L));
		orders.add(new Orders("123 avenue", "17th January 2020", 1L));
		Mockito.when(ordersServices.readAll()).thenReturn(orders);
		assertEquals(orders, ordersController.readAll());
	}

	@Test
	public void createTest() {
		String order_address = "123 road";
		String order_date = "19th January 2020";
		Long customerid = 1L;
		Mockito.doReturn(order_address, order_date).when(ordersController).getInput();
		Mockito.doReturn(customerid).when(ordersController).getInputL();
		Orders orders = new Orders(order_address, order_date, customerid);
		Orders savedOrder = new Orders(1L, "123 road", "19th January 2020", 1L);
		Mockito.when(ordersServices.create(orders)).thenReturn(savedOrder);
		assertEquals(savedOrder, ordersController.create());
	}

	@Test
	public void updateTest() {
		String id = "1";
		String order_address = "123 road";
		String order_date = "19th January 2021";
		Long customerid = 1L;
		Mockito.doReturn(id, order_address, order_date).when(ordersController).getInput();
		Mockito.doReturn(customerid).when(ordersController).getInputL();
		Orders orders = new Orders(1L, order_address, order_date, customerid);
		Mockito.when(ordersServices.update(orders)).thenReturn(orders);
		assertEquals(orders, ordersController.update());
	}

	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(ordersController).getInput();
		ordersController.delete();
		Mockito.verify(ordersServices, Mockito.times(1)).delete(1L);
	}

}
