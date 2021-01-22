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

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.services.ItemsServices;

@RunWith(MockitoJUnitRunner.class)
public class ItemsControllerTest {

	@Mock
	private ItemsServices itemsServices;

	@Spy
	@InjectMocks
	private ItemsController itemsController;

	@Test
	public void readAllTest() {
		ItemsController itemsController = new ItemsController(itemsServices);
		List<Items> items = new ArrayList<>();
		items.add(new Items("item1", 300.00));
		items.add(new Items("item2", 500.00));
		items.add(new Items("item3", 100.00));
		Mockito.when(itemsServices.readAll()).thenReturn(items);
		assertEquals(items, itemsController.readAll());
	}

	@Test
	public void createTest() {
		String itemName = "item1";
		Double Price = 300.00;
		Mockito.doReturn(itemName).when(itemsController).getInput();
		Mockito.doReturn(Price).when(itemsController).getInputD();
		Items items = new Items(itemName, Price);
		Items savedItem = new Items(1L, "item1", 300.00);
		Mockito.when(itemsServices.create(items)).thenReturn(savedItem);
		assertEquals(savedItem, itemsController.create());
	}

	@Test
	public void updateTest() {
		String id = "1";
		String itemName = "itemU";
		Double Price = 200.00;
		Mockito.doReturn(id, itemName).when(itemsController).getInput();
		Mockito.doReturn(Price).when(itemsController).getInputD();
		Items items = new Items(1L, itemName, Price);
		Mockito.when(itemsServices.update(items)).thenReturn(items);
		assertEquals(items, itemsController.update());
	}

	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(itemsController).getInput();
		itemsController.delete();
		Mockito.verify(itemsServices, Mockito.times(1)).delete(1L);
	}

}
