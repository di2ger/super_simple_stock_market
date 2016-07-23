package com.company.stock.super_simple_stock_market.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.ConfigurationException;

import com.company.stock.super_simple_stock_market.engine.calculators.Calculator;
import com.company.stock.super_simple_stock_market.engine.data_types.ResultData;
import com.company.stock.super_simple_stock_market.model.Trade;

public class Engine {
	@SuppressWarnings("rawtypes")
	private Map<CalculationType, Calculator> calculators;
	private final List<Trade> trades = new ArrayList<>();
	
	public <I, O> ResultData<O> calculate(CalculationType calculationType, I input) throws ConfigurationException {
		@SuppressWarnings("unchecked")
		Calculator<I, O> calculator = calculators.get(calculationType);
		if (calculator == null) {
			throw new ConfigurationException("CalculationType " + calculationType + " is not configured");
		}
		return calculator.apply(input);
	}
	
	public @SuppressWarnings("rawtypes") Map<CalculationType, Calculator> getCalculators() {
		return calculators;
	}
	public void setCalculators(@SuppressWarnings("rawtypes") Map<CalculationType, Calculator> calculators) {
		this.calculators = calculators;
	}

	public List<Trade> getTrades() {
		return trades;
	}

}
