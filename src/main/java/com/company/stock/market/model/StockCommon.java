package com.company.stock.market.model;

public class StockCommon extends Stock {

	@Override
	public StockType getType() {
		return StockType.COMMON;
	}

}
