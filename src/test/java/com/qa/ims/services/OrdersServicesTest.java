package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Orders;

@RunWith(MockitoJUnitRunner.class)
public class OrdersServicesTest {

	@Mock
	private Dao<Orders> ordersDao;

	@InjectMocks
	private OrdersServices ordersServices;

	@Test
	public void ordersServicesCreate() {
		Orders orders = new Orders("123 road", "19th January 2020", 1L);
		ordersServices.create(orders);
		Mockito.verify(ordersDao, Mockito.times(1)).create(orders);
	}

	@Test
	public void ordersServicesRead() {
		ordersServices.readAll();
		Mockito.verify(ordersDao, Mockito.times(1)).readAll();
	}

	@Test
	public void ordersServicesUpdate() {
		Orders orders = new Orders("123 road", "19th January 2020", 2L);
		ordersServices.update(orders);
		Mockito.verify(ordersDao, Mockito.times(1)).update(orders);
	}

	@Test
	public void ordersServicesDelete() {
		ordersServices.delete(1L);
		;
		Mockito.verify(ordersDao, Mockito.times(1)).delete(1L);
	}

}
