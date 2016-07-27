package com.company.stock.market.engine.data.wrappers;

import com.company.stock.market.api.model.Stock;

public class StockAndPrice {
	private Stock stock;
	// in pennies
	private long price;
	
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
}
