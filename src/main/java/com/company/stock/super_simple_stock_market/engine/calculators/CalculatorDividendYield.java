package com.company.stock.super_simple_stock_market.engine.calculators;

import com.company.stock.super_simple_stock_market.engine.data_types.ResultData;
import com.company.stock.super_simple_stock_market.engine.data_types.StockAndPrice;
import com.company.stock.super_simple_stock_market.model.Stock;
import com.company.stock.super_simple_stock_market.model.StockPreferred;
import com.company.stock.super_simple_stock_market.model.StockType;

public class CalculatorDividendYield extends Calculator<StockAndPrice, Double> {

	@Override
	public ResultData<Double> calculate(StockAndPrice inputData) {
		Stock stock = inputData.getStock();
		Integer price = inputData.getPrice();
		ResultData<Double> resultData = new ResultData<>();
		throwIfZero(price, "price");
		if (stock.getType() == StockType.COMMON) {
			throwIfNull(stock.getLastDividend(), "Last Dividend");
			resultData.setResult(1d*stock.getLastDividend()/price);
		} else if (stock.getType() == StockType.PREFERRED) {
			StockPreferred stockPreferred = (StockPreferred)stock;
			throwIfNull(stockPreferred.getFixedDividend(), "Fixed Dividend");
			throwIfNull(stockPreferred.getParValue(), "Par Value");
			resultData.setResult(1d*stockPreferred.getFixedDividend()*stockPreferred.getParValue()/(100d*price));
		} else {
			throw new IllegalArgumentException("Unknown Stock type: " + stock.getType());
		}
		return resultData;
	}

}
