package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Items;

public class ItemsServices implements CrudServices<Items> {

	private Dao<Items> itemsDao;

	public ItemsServices(Dao<Items> itemsDao) {
		this.itemsDao = itemsDao;
	}

	public List<Items> readAll() {
		return itemsDao.readAll();
	}

	public Items create(Items item) {
		return itemsDao.create(item);
	}

	public Items update(Items item) {
		return itemsDao.update(item);
	}

	public void delete(Long id) {
		itemsDao.delete(id);
	}

}
