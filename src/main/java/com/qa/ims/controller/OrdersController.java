package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrdersController implements CrudController<Orders> {

	public static final Logger LOGGER = Logger.getLogger(OrdersController.class);

	private CrudServices<Orders> ordersService;

	public OrdersController(CrudServices<Orders> ordersService) {
		this.ordersService = ordersService;

	}

	String getInput() {
		return Utils.getInput();
	}

	Double getInputD() {
		return Utils.getInputD();
	}

	Long getInputL() {
		return Utils.getInputL();
	}

	@Override
	public List<Orders> readAll() {
		List<Orders> orders = ordersService.readAll();
		for (Orders order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	@Override
	public Orders create() {
		LOGGER.info("Please enter the address of the order");
		String OrderAddress = getInput();
		LOGGER.info("Please enter the date the order was placed");
		String OrderDate = getInput();
		LOGGER.info("Please enter the id of the customer that placed the order");
		Long CustomerID = getInputL();
		Orders orders = ordersService.create(new Orders(OrderAddress, OrderDate, CustomerID));
		LOGGER.info("Order created");
		return orders;
	}

	@Override
	public Orders update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = Long.valueOf(getInput());
		LOGGER.info("Please enter the address of the order");
		String OrderAddress = getInput();
		LOGGER.info("Please enter the date the order was placed");
		String OrderDate = getInput();
		LOGGER.info("Please enter the id of the customer that placed the order");
		Long CustomerID = getInputL();
		Orders orders = ordersService.update(new Orders(id, OrderAddress, OrderDate, CustomerID));
		LOGGER.info("Order created");
		return orders;

	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = Long.valueOf(getInput());
		ordersService.delete(id);
		LOGGER.info("OrderDeleted");

	}

}
