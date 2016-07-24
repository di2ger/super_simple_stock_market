package com.company.stock.super_simple_stock_market.engine;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.company.stock.super_simple_stock_market.engine.calculators.Calculator;
import com.company.stock.super_simple_stock_market.engine.data_types.CollectionOfTrades;
import com.company.stock.super_simple_stock_market.engine.data_types.ResultData;
import com.company.stock.super_simple_stock_market.engine.data_types.StockAndCollectionOfTradesAndInterval;
import com.company.stock.super_simple_stock_market.engine.data_types.StockAndPrice;
import com.company.stock.super_simple_stock_market.model.Trade;

public class Engine {
	private Calculator<StockAndPrice, Double> calculatorDividendYield;
	private Calculator<StockAndPrice, Double> calculatorPeRatio;
	private Calculator<StockAndCollectionOfTradesAndInterval, Double> calculatorVolumeWeightedStockPrice;
	private Calculator<CollectionOfTrades, Double> calculatorGbceAllShareIndex;
	
	// Concurrency supported for adding trades from several threads
	private final Queue<Trade> trades = new ConcurrentLinkedQueue<>();
	
	public ResultData<Double> calculateDividendYield (StockAndPrice input) {
		return calculatorDividendYield.apply(input);
	}
	
	public ResultData<Double> calculatePeRatio (StockAndPrice input) {
		return calculatorPeRatio.apply(input);
	}
	
	public ResultData<Double> calculateVolumeWeightedStockPrice (StockAndCollectionOfTradesAndInterval input) {
		return calculatorVolumeWeightedStockPrice.apply(input);
	}
	
	public ResultData<Double> calculateGbceAllShareIndex (CollectionOfTrades input) {
		return calculatorGbceAllShareIndex.apply(input);
	}

	// Plain getters and setters from this point
	
	public Queue<Trade> getTrades() {
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
