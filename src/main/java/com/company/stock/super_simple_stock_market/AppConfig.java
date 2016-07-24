package com.company.stock.super_simple_stock_market;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.company.stock.super_simple_stock_market.engine.Engine;
import com.company.stock.super_simple_stock_market.engine.calculators.Calculator;
import com.company.stock.super_simple_stock_market.engine.calculators.CalculatorDividendYield;
import com.company.stock.super_simple_stock_market.engine.calculators.CalculatorGbceAllShareIndex;
import com.company.stock.super_simple_stock_market.engine.calculators.CalculatorPeRatio;
import com.company.stock.super_simple_stock_market.engine.calculators.CalculatorVolumeWeightedStockPrice;
import com.company.stock.super_simple_stock_market.engine.data_types.CollectionOfTrades;
import com.company.stock.super_simple_stock_market.engine.data_types.StockAndCollectionOfTradesAndInterval;
import com.company.stock.super_simple_stock_market.engine.data_types.StockAndPrice;

@Configuration
public class AppConfig {
	@Bean
	public Engine engine() {
		Engine engine = new Engine();
		engine.setCalculatorDividendYield(calculatorDividendYield());
		engine.setCalculatorPeRatio(calculatorPeRatio());
		engine.setCalculatorVolumeWeightedStockPrice(calculatorVolumeWeightedStockPrice());
		engine.setCalculatorGbceAllShareIndex(calculatorGbceAllShareIndex());
		return engine;
	}

	public Calculator<StockAndPrice, Double> calculatorDividendYield() {
		return new CalculatorDividendYield();
	}
	
	public Calculator<StockAndPrice, Double> calculatorPeRatio() {
		return new CalculatorPeRatio();
	}
	
	public Calculator<StockAndCollectionOfTradesAndInterval, Double> calculatorVolumeWeightedStockPrice() {
		return new CalculatorVolumeWeightedStockPrice();
	}
	
	public Calculator<CollectionOfTrades, Double> calculatorGbceAllShareIndex() {
		return new CalculatorGbceAllShareIndex();
	}

}
