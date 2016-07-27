package com.company.stock.market.engine.calculators;

import com.company.stock.market.api.model.ResultData;
import com.company.stock.market.api.model.Stock;

public class CalculatorDividendCommon extends Calculator<Stock, Double> {

	@Override
	public ResultData<Double> calculate(Stock stock) {
		ResultData<Double> resultData = new ResultData<>();
		resultData.setResult(1d*stock.getLastDividend());
		return resultData;
	}

}
