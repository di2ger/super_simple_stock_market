package com.company.stock.super_simple_stock_market.engine.data_types;

import com.company.stock.super_simple_stock_market.model.Stock;

public class StockAndPrice {
	private Stock stock;
	// in pennies
	private Integer price;
	
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
}
