package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderItemsController implements CrudController<OrderItems> {

	public static final Logger LOGGER = Logger.getLogger(OrderItemsController.class);

	private CrudServices<OrderItems> orderitemsService;

	public OrderItemsController(CrudServices<OrderItems> orderItemsService) {
		this.orderitemsService = orderItemsService;
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

	Integer getInputI() {
		return Utils.getInputI();
	}

	Boolean getInputB() {
		return Utils.getInputB();
	}

	@Override
	public List<OrderItems> readAll() {
		List<OrderItems> orderitems = orderitemsService.readAll();
		for (OrderItems orderItems : orderitems) {
			LOGGER.info(orderItems.toString());
		}
		return orderitems;
	}

	@Override
	public OrderItems create() {
		Double TotalCost = 0.0;
		LOGGER.info("Would you like to add items to an order? 1 for YES, 0 for NO");
		int inputForNextBuy = getInputI();
		do {
			LOGGER.info("Please enter the ID of the order you would like to add item(s) to");
			Long orderID = getInputL();
			LOGGER.info("Please enter the ID of the item you would like to add to the order");
			Long itemID = getInputL();
			LOGGER.info("Please enter quantity you would like to add");
			Integer quantity = getInputI();
			OrderItems Orderitems = orderitemsService.create(new OrderItems(itemID, orderID, quantity));
			Double price = Orderitems.getPrice();
			Double Amount = quantity * price;
			System.out.println("Amount = " + Amount);
			TotalCost = TotalCost + Amount;
			System.out.println("Total Cost = " + TotalCost);
			LOGGER.info("Item added to order");
			LOGGER.info("Would you like to add items to an order? 1 for YES, 0 for NO");
			inputForNextBuy = getInputI();

		} while (inputForNextBuy == 1);

		return null;

	}

	@Override
	public OrderItems update() {
		LOGGER.info("Please enter the id of the order you would like to update the items on");
		Long itemID = Long.valueOf(getInput());
		LOGGER.info("Please enter the id of the item in the order you would like to add");
		Long orderID = getInputL();
		LOGGER.info("Please enter the quantity you would like to order of the item");
		Integer quantity = getInputI();
		OrderItems Orderitems = orderitemsService.create(new OrderItems(itemID, orderID, quantity));
		LOGGER.info("Item in order updated");
		return Orderitems;
	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the order_items you would like to delete");
		Long id = Long.valueOf(getInput());
		orderitemsService.delete(id);
		LOGGER.info("Order Deleted");
	}

}
