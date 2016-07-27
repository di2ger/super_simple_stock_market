package com.company.stock.market.api.model;

import java.util.Date;

public class Trade {
	private Stock stock;
	private Date timestamp;
	private long quantity;
	private boolean buyIndicator;
	// in pennies
	private long price;
	
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock unit) {
		this.stock = unit;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public Boolean getBuyIndicator() {
		return buyIndicator;
	}
	public void setBuyIndicator(boolean buyIndicator) {
		this.buyIndicator = buyIndicator;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Trade [stock=" + stock + ", timestamp=" + timestamp + ", quantity=" + quantity + ", buyIndicator="
				+ buyIndicator + ", price=" + price + "]";
	}

}
