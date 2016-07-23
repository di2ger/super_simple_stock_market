package com.company.stock.super_simple_stock_market;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.company.stock.super_simple_stock_market.engine.CalculationType;
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
		engine.setCalculators(calculators());
		return engine;
	}

	@SuppressWarnings("rawtypes")
	@Bean
	public Map<CalculationType, Calculator> calculators() {
		Map<CalculationType, Calculator> map = new EnumMap<>(CalculationType.class);
		map.put(CalculationType.DIVIDEND_YIELD, calculatorDividendYield());
		map.put(CalculationType.PE_RATIO, calculatorPeRatio());
		map.put(CalculationType.VOLUME_WEIGHTED_STOCK_PRICE, calculatorVolumeWeightedStockPrice());
		map.put(CalculationType.GBCE_ALL_SHARE_INDEX, calculatorGbceAllShareIndex());

		return Collections.unmodifiableMap(map);
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
		return new CalculatorVolumeWeightedStockPrice();
	}
	
	@Bean
	public Calculator<CollectionOfTrades, Double> calculatorGbceAllShareIndex() {
		return new CalculatorGbceAllShareIndex();
	}

}
