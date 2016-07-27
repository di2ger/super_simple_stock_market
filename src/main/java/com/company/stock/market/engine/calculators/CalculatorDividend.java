package com.company.stock.market.engine.calculators;

import java.util.Map;

import com.company.stock.market.api.model.ResultData;
import com.company.stock.market.api.model.Stock;
import com.company.stock.market.api.model.StockType;

public class CalculatorDividend extends Calculator<Stock, Double> {
	private Map<StockType, Calculator<Stock, Double>> calculators;

	@Override
	public ResultData<Double> calculate(Stock stock) {
		Calculator<Stock, Double> calculator = calculators.get(stock.getType());
		if (calculator == null) {
			throw new IllegalArgumentException("Unknown Stock type: " + stock.getType());
		}
		return calculator.apply(stock);
	}

	public void setCalculators(Map<StockType, Calculator<Stock, Double>> calculators) {
		this.calculators = calculators;
	}
	
	public Map<StockType, Calculator<Stock, Double>> getCalculators() {
		return calculators;
	}

}
