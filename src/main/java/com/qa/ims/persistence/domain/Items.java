package com.qa.ims.persistence.domain;

public class Items {
	private Long id;
	private String ItemName;
	private Double Price;

	public Items(String ItemName, Double Price) {
		this.ItemName = ItemName;
		this.Price = Price;
	}

	public Items(Long id, String ItemName, Double Price) {
		this.id = id;
		this.ItemName = ItemName;
		this.Price = Price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String ItemName) {
		this.ItemName = ItemName;
	}

	public Double getPrice() {
		return Price;
	}

	public void setSurname(Double Price) {
		this.Price = Price;
	}

	public String toString() {
		return "id: " + id + " Item name: " + ItemName + " Price: " + Price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ItemName == null) ? 0 : ItemName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((Price == null) ? 0 : Price.hashCode());
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
		Items other = (Items) obj;
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
		if (Price == null) {
			if (other.Price != null)
				return false;
		} else if (!Price.equals(other.Price))
			return false;
		return true;
	}

}
