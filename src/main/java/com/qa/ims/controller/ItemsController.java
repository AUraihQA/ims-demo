package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class ItemsController implements CrudController<Items> {

	public static final Logger LOGGER = Logger.getLogger(ItemsController.class);

	private CrudServices<Items> itemsService;

	public ItemsController(CrudServices<Items> itemsService) {
		this.itemsService = itemsService;
	}

	String getInput() {
		return Utils.getInput();
	}

	Double getInputD() {
		return Utils.getInputD();
	}

	@Override
	public List<Items> readAll() {
		List<Items> items = itemsService.readAll();
		for (Items item : items) {
			LOGGER.info(item.toString());
		}
		return items;
	}

	@Override
	public Items create() {
		LOGGER.info("Please enter the item name");
		String itemName = getInput();
		LOGGER.info("Please enter the price of the Item (£)");
		Double Price = getInputD();
		Items items = itemsService.create(new Items(itemName, Price));
		LOGGER.info("Item created");
		return items;
	}

	public Items update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = Long.valueOf(getInput());
		LOGGER.info("Please enter the Item name");
		String itemName = getInput();
		LOGGER.info("Please enter the Price");
		Double Price = getInputD();
		Items items = itemsService.update(new Items(id, itemName, Price));
		LOGGER.info("Item Updated");
		return items;
	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = Long.valueOf(getInput());
		itemsService.delete(id);
		LOGGER.info("Item Deleted");
	}

}
