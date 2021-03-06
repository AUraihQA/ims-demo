package com.qa.ims.persistence.domain;

public class OrderItems {
	private Long id;
	private Long itemID;
	private Long orderID;
	private Double price;
	private Integer quantity;
	private String ItemName;

	public OrderItems(Long itemID, Long orderID, Integer quantity, Double price, String ItemName) {
		this.itemID = itemID;
		this.orderID = orderID;
		this.quantity = quantity;
		this.price = price;
		this.ItemName = ItemName;
	}

	public OrderItems(Long id, Long itemID, Long orderID, Integer quantity, Double price, String ItemName) {
		this.id = id;
		this.itemID = itemID;
		this.orderID = orderID;
		this.quantity = quantity;
		this.price = price;
		this.ItemName = ItemName;
	}

	public OrderItems(Long itemID, Long orderID, Integer quantity) {
		this.itemID = itemID;
		this.orderID = orderID;
		this.quantity = quantity;
	}

	public OrderItems(Long id, Long itemID, Long orderID, Integer quantity) {
		this.id = id;
		this.itemID = itemID;
		this.orderID = orderID;
		this.quantity = quantity;
	}

	public OrderItems(Double price) {
		super();
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemID() {
		return itemID;
	}

	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	@Override
	public String toString() {
		return "id:" + id + " ItemID:" + itemID + " OrderID:" + orderID + " ItemName:" + ItemName + " Price:" + price
				+ " Quantity:" + quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ItemName == null) ? 0 : ItemName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemID == null) ? 0 : itemID.hashCode());
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItems other = (OrderItems) obj;
		if (ItemName == null) {
			if (other.ItemName != null)
				return false;
		} else if (!ItemName.equals(other.ItemName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemID == null) {
			if (other.itemID != null)
				return false;
		} else if (!itemID.equals(other.itemID))
			return false;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

}
