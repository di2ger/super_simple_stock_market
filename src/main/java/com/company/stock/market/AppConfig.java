package com.company.stock.market;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.company.stock.market.engine.Engine;
import com.company.stock.market.engine.calculators.Calculator;
import com.company.stock.market.engine.calculators.CalculatorDividendYield;
import com.company.stock.market.engine.calculators.CalculatorGbceAllShareIndex;
import com.company.stock.market.engine.calculators.CalculatorPeRatio;
import com.company.stock.market.engine.calculators.CalculatorVolumeWeightedStockPrice;
import com.company.stock.market.engine.data_types.CollectionOfTrades;
import com.company.stock.market.engine.data_types.StockAndCollectionOfTradesAndInterval;
import com.company.stock.market.engine.data_types.StockAndPrice;

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

	@Bean
	public Calculator<StockAndPrice, Double> calculatorDividendYield() {
		return new CalculatorDividendYield();
	}
	
	@Bean
	public Calculator<StockAndPrice, Double> calculatorPeRatio() {
		return new CalculatorPeRatio();
	}
	
	@Bean
	public Calculator<StockAndCollectionOfTradesAndInterval, Double> calculatorVolumeWeightedStockPrice() {
		CalculatorVolumeWeightedStockPrice calculator = new CalculatorVolumeWeightedStockPrice();
		
		return calculator;
	}
	
	@Bean
	public Calculator<CollectionOfTrades, Double> calculatorGbceAllShareIndex() {
		return new CalculatorGbceAllShareIndex();
	}

}