package com.company.stock.market.engine.data.wrappers;

import java.util.Collection;

import com.company.stock.market.api.model.Stock;
import com.company.stock.market.api.model.Trade;

public class StockAndCollectionOfTradesAndInterval {
	private Stock stock; 
	private Collection<Trade> trades;
	// in seconds
	private int interval;
	
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
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
}
