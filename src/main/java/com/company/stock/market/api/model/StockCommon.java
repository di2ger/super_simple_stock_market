package com.company.stock.market.api.model;

public class StockCommon extends Stock {

	@Override
	public StockType getType() {
		return StockType.COMMON;
	}

}
