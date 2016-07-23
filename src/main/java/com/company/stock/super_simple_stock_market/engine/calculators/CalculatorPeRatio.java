package com.company.stock.super_simple_stock_market.engine.calculators;

import com.company.stock.super_simple_stock_market.engine.data_types.ResultData;
import com.company.stock.super_simple_stock_market.engine.data_types.StockAndPrice;
import com.company.stock.super_simple_stock_market.model.Stock;
import com.company.stock.super_simple_stock_market.model.StockPreferred;
import com.company.stock.super_simple_stock_market.model.StockType;

public class CalculatorPeRatio extends Calculator<StockAndPrice, Double> {

	@Override
	public ResultData<Double> calculate(StockAndPrice inputData) {
		Stock stock = inputData.getStock();
		Integer price = inputData.getPrice();
		ResultData<Double> resultData = new ResultData<>();
		throwIfNull(price, "price");
		if (stock.getType().equals(StockType.COMMON)) {
			throwIfZero(stock.getLastDividend(), "Last Dividend");
			resultData.setResult(1d*price/stock.getLastDividend());
		} else if (stock.getType().equals(StockType.PREFERRED)) {
			StockPreferred stockPreferred = (StockPreferred)stock;
			throwIfZero(stockPreferred.getFixedDividend(), "Fixed Dividend");
			throwIfZero(stockPreferred.getParValue(), "Par Value");
			resultData.setResult(100d*price/(stockPreferred.getFixedDividend()*stockPreferred.getParValue()));
		} else {
			throw new IllegalArgumentException("Unknown Stock type: " + stock.getType());
		}
		return resultData;
	}

}
