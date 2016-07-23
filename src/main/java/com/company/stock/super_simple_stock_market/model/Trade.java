package com.company.stock.super_simple_stock_market.model;

import java.util.Date;

public class Trade {
	private Stock stock;
	private Date timestamp;
	private Integer quantity;
	private Boolean buyIndicator;
	// in pennies
	private Integer price;
	
	public Stock getUnit() {
		return stock;
	}
	public void setUnit(Stock unit) {
		this.stock = unit;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Boolean getBuyIndicator() {
		return buyIndicator;
	}
	public void setBuyIndicator(Boolean buyIndicator) {
		this.buyIndicator = buyIndicator;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	

}
