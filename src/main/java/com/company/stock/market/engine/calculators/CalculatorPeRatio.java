package com.company.stock.market.engine.calculators;

import com.company.stock.market.api.model.ResultData;
import com.company.stock.market.api.model.Stock;
import com.company.stock.market.engine.data.wrappers.StockAndPrice;

public class CalculatorPeRatio extends Calculator<StockAndPrice, Double> {
	
	private Calculator<Stock, Double> calculatorDividend;

	@Override
	public ResultData<Double> calculate(StockAndPrice inputData) {
		
		Stock stock = inputData.getStock();
		long price = inputData.getPrice();
		ResultData<Double> resultData = new ResultData<>();
		
		ResultData<Double> dividend = calculatorDividend.apply(stock);
		if (dividend.getErrorDescription() != null) {
			throw new IllegalArgumentException("Dividend calculation error: " + dividend.getErrorDescription());
		}
		throwIfZero(dividend.getResult(), "Dividend");
		resultData.setResult(price/dividend.getResult());
		return resultData;
	}

	public Calculator<Stock, Double> getCalculatorDividend() {
		return calculatorDividend;
	}

	public void setCalculatorDividend(Calculator<Stock, Double> calculatorDividend) {
		this.calculatorDividend = calculatorDividend;
	}

}
