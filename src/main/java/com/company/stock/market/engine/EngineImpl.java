package com.company.stock.market.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.company.stock.market.api.model.ResultData;
import com.company.stock.market.api.model.Stock;
import com.company.stock.market.api.model.Trade;
import com.company.stock.market.api.service.Engine;
import com.company.stock.market.engine.calculators.Calculator;
import com.company.stock.market.engine.data.wrappers.CollectionOfTrades;
import com.company.stock.market.engine.data.wrappers.StockAndCollectionOfTradesAndInterval;
import com.company.stock.market.engine.data.wrappers.StockAndPrice;

public class EngineImpl implements Engine {
	private Calculator<StockAndPrice, Double> calculatorDividendYield;
	private Calculator<StockAndPrice, Double> calculatorPeRatio;
	private Calculator<StockAndCollectionOfTradesAndInterval, Double> calculatorVolumeWeightedStockPrice;
	private Calculator<CollectionOfTrades, Double> calculatorGbceAllShareIndex;
	
	private final List<Trade> trades = new ArrayList<>();
	
	public ResultData<Double> calculateDividendYield (Stock stock, long price) {
		StockAndPrice input = new StockAndPrice();
		input.setStock(stock);
		input.setPrice(price);
		return calculatorDividendYield.apply(input);
	}
	
	public ResultData<Double> calculatePeRatio (Stock stock, long price) {
		StockAndPrice input = new StockAndPrice();
		input.setStock(stock);
		input.setPrice(price);
		return calculatorPeRatio.apply(input);
	}
	
	public ResultData<Double> calculateVolumeWeightedStockPrice (Stock stock, Collection<Trade> trades, int interval) {
		StockAndCollectionOfTradesAndInterval input = new StockAndCollectionOfTradesAndInterval();
		input.setStock(stock);
		input.setTrades(trades);
		input.setInterval(interval);
		return calculatorVolumeWeightedStockPrice.apply(input );
	}
	
	public ResultData<Double> calculateGbceAllShareIndex (Collection<Trade> trades) {
		CollectionOfTrades input = new CollectionOfTrades();
		input.setTrades(trades);
		return calculatorGbceAllShareIndex.apply(input);
	}

	// Plain getters and setters from this point
	
	public List<Trade> getTrades() {
		return trades;
	}

	public Calculator<StockAndPrice, Double> getCalculatorDividendYield() {
		return calculatorDividendYield;
	}

	public void setCalculatorDividendYield(Calculator<StockAndPrice, Double> calculatorDividendYield) {
		this.calculatorDividendYield = calculatorDividendYield;
	}

	public Calculator<StockAndPrice, Double> getCalculatorPeRatio() {
		return calculatorPeRatio;
	}

	public void setCalculatorPeRatio(Calculator<StockAndPrice, Double> calculatorPeRatio) {
		this.calculatorPeRatio = calculatorPeRatio;
	}

	public Calculator<StockAndCollectionOfTradesAndInterval, Double> getCalculatorVolumeWeightedStockPrice() {
		return calculatorVolumeWeightedStockPrice;
	}

	public void setCalculatorVolumeWeightedStockPrice(
			Calculator<StockAndCollectionOfTradesAndInterval, Double> calculatorVolumeWeightedStockPrice) {
		this.calculatorVolumeWeightedStockPrice = calculatorVolumeWeightedStockPrice;
	}

	public Calculator<CollectionOfTrades, Double> getCalculatorGbceAllShareIndex() {
		return calculatorGbceAllShareIndex;
	}

	public void setCalculatorGbceAllShareIndex(Calculator<CollectionOfTrades, Double> calculatorGbceAllShareIndex) {
		this.calculatorGbceAllShareIndex = calculatorGbceAllShareIndex;
	}
}
