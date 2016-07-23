package com.company.stock.super_simple_stock_market.model;

public class StockCommon extends Stock {

	@Override
	public StockType getType() {
		return StockType.COMMON;
	}

}
