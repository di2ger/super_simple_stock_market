package com.company.stock.super_simple_stock_market.engine.data_types;

import java.util.Collection;

import com.company.stock.super_simple_stock_market.model.Trade;

public class CollectionOfTrades {
	private Collection<Trade> trades;
	
	public Collection<Trade> getTrades() {
		return trades;
	}
	public void setTrades(Collection<Trade> trades) {
		this.trades = trades;
	}
}
