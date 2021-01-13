package com.qa.ims.persistence.domain;

public class Orders {
	private Long id;
	private String OrderAddress;
	private String OrderDate;
	private Long CustomerID;

	public Orders(String orderAddress, String orderDate, Long customerID) {
		super();
		OrderAddress = orderAddress;
		OrderDate = orderDate;
		CustomerID = customerID;
	}

	public Orders(Long id, String orderAddress, String orderDate, Long customerID) {
		super();
		this.id = id;
		OrderAddress = orderAddress;
		OrderDate = orderDate;
		CustomerID = customerID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderAddress() {
		return OrderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		OrderAddress = orderAddress;
	}

	public String getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}

	public Long getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(Long customerID) {
		CustomerID = customerID;
	}

	@Override
	public String toString() {
		return "id:" + id + " OrderAddress:" + OrderAddress + " OrderDate:" + OrderDate + " CustomerID:" + CustomerID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CustomerID == null) ? 0 : CustomerID.hashCode());
		result = prime * result + ((OrderAddress == null) ? 0 : OrderAddress.hashCode());
		result = prime * result + ((OrderDate == null) ? 0 : OrderDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Orders other = (Orders) obj;
		if (CustomerID == null) {
			if (other.CustomerID != null)
				return false;
		} else if (!CustomerID.equals(other.CustomerID))
			return false;
		if (OrderAddress == null) {
			if (other.OrderAddress != null)
				return false;
		} else if (!OrderAddress.equals(other.OrderAddress))
			return false;
		if (OrderDate == null) {
			if (other.OrderDate != null)
				return false;
		} else if (!OrderDate.equals(other.OrderDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
