package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.OrderItems;

@RunWith(MockitoJUnitRunner.class)
public class OrderItemsServicesTest {
	@Mock
	private Dao<OrderItems> orderItemsDao;

	@InjectMocks
	private OrderItemsServices orderItemsServices;

	@Test
	public void orderItemsServicesCreate() {
		OrderItems orderItems = new OrderItems(1L, 1L, 2);
		orderItemsServices.create(orderItems);
		Mockito.verify(orderItemsDao, Mockito.times(1)).create(orderItems);
	}

	@Test
	public void orderItemsServicesRead() {
		orderItemsServices.readAll();
		Mockito.verify(orderItemsDao, Mockito.times(1)).readAll();
	}

	@Test
	public void orderItemsServicesUpdate() {
		OrderItems orderItems = new OrderItems(1L, 1L, 10);
		orderItemsServices.update(orderItems);
		Mockito.verify(orderItemsDao, Mockito.times(1)).update(orderItems);
	}

	@Test
	public void orderItemsServicesDelete() {
		orderItemsServices.delete(1L);
		Mockito.verify(orderItemsDao, Mockito.times(1)).delete(1L);
	}

}
