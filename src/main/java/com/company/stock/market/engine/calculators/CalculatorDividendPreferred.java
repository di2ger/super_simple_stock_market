package com.company.stock.market.engine.calculators;

import com.company.stock.market.api.model.ResultData;
import com.company.stock.market.api.model.Stock;
import com.company.stock.market.api.model.StockPreferred;

public class CalculatorDividendPreferred extends Calculator<Stock, Double> {

	@Override
	public ResultData<Double> calculate(Stock inputData) {
		if (!(inputData instanceof StockPreferred)) {
			throw new IllegalArgumentException("Stock is not StockPreferred: " + inputData);
		}
		StockPreferred stock = (StockPreferred)inputData;
		ResultData<Double> resultData = new ResultData<>();

		throwIfZero(stock.getFixedDividend(), "Fixed Dividend");
		throwIfZero(stock.getParValue(), "Par Value");
		resultData.setResult(stock.getFixedDividend()*stock.getParValue()/100d);
		return resultData;
	}

}
