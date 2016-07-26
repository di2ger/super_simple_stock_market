package com.company.stock.market.engine.calculators;

import com.company.stock.market.model.ResultData;
import com.company.stock.market.model.Stock;

public class CalculatorDividendCommon extends Calculator<Stock, Double> {

	@Override
	public ResultData<Double> calculate(Stock stock) {
		ResultData<Double> resultData = new ResultData<>();
		resultData.setResult(1d*stock.getLastDividend());
		return resultData;
	}

}
