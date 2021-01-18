package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.OrderItems;

public class OrderItemsServices implements CrudServices<OrderItems> {

	private Dao<OrderItems> orderItemsDao;

	public OrderItemsServices(Dao<OrderItems> orderItemsDao) {
		this.orderItemsDao = orderItemsDao;
	}

	@Override
	public List<OrderItems> readAll() {
		// TODO Auto-generated method stub
		return orderItemsDao.readAll();
	}

	@Override
	public OrderItems create(OrderItems orderItems) {
		// TODO Auto-generated method stub
		return orderItemsDao.create(orderItems);
	}

	@Override
	public OrderItems update(OrderItems orderItems) {
		// TODO Auto-generated method stub
		return orderItemsDao.update(orderItems);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		orderItemsDao.delete(id);

	}

}
