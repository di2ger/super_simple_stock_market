package com.company.stock.market.engine.data_types;

import java.util.Collection;

import com.company.stock.market.model.Stock;
import com.company.stock.market.model.Trade;

public class StockAndCollectionOfTradesAndInterval {
	private Stock stock; 
	private Collection<Trade> trades;
	// in seconds
	private Integer interval;
	
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public Collection<Trade> getTrades() {
		return trades;
	}
	public void setTrades(Collection<Trade> trades) {
		this.trades = trades;
	}
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
}
