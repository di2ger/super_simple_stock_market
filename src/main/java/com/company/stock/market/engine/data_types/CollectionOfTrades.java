package com.company.stock.market.engine.data_types;

import java.util.Collection;

import com.company.stock.market.model.Trade;

public class CollectionOfTrades {
	private Collection<Trade> trades;
	
	public Collection<Trade> getTrades() {
		return trades;
	}
	public void setTrades(Collection<Trade> trades) {
		this.trades = trades;
	}
}
