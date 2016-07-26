package com.company.stock.market;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.company.stock.market.engine.EngineImpl;
import com.company.stock.market.engine.calculators.Calculator;
import com.company.stock.market.engine.calculators.CalculatorDividend;
import com.company.stock.market.engine.calculators.CalculatorDividendYield;
import com.company.stock.market.engine.calculators.CalculatorGbceAllShareIndex;
import com.company.stock.market.engine.calculators.CalculatorPeRatio;
import com.company.stock.market.engine.calculators.CalculatorDividendCommon;
import com.company.stock.market.engine.calculators.CalculatorDividendPreferred;
import com.company.stock.market.engine.calculators.CalculatorVolumeWeightedStockPrice;
import com.company.stock.market.engine.data.wrappers.CollectionOfTrades;
import com.company.stock.market.engine.data.wrappers.StockAndCollectionOfTradesAndInterval;
import com.company.stock.market.engine.data.wrappers.StockAndPrice;
import com.company.stock.market.model.Stock;
import com.company.stock.market.model.StockType;

@Configuration
public class AppConfig {
	@Bean
	public EngineImpl engine() {
		EngineImpl engine = new EngineImpl();
		engine.setCalculatorDividendYield(calculatorDividendYield());
		engine.setCalculatorPeRatio(calculatorPeRatio());
		engine.setCalculatorVolumeWeightedStockPrice(calculatorVolumeWeightedStockPrice());
		engine.setCalculatorGbceAllShareIndex(calculatorGbceAllShareIndex());
		return engine;
	}
	
	@Bean
	public Calculator<Stock, Double> calculatorDividendCommon() {
		return new CalculatorDividendCommon();
	}
	
	@Bean
	public Calculator<Stock, Double> calculatorDividendPreferred() {
		return new CalculatorDividendPreferred();
	}
	
	@Bean
	public Map<StockType, Calculator<Stock, Double>> calculatorsDividendMap() {
		Map<StockType, Calculator<Stock, Double>> map = new EnumMap<>(StockType.class);
		map.put(StockType.COMMON, calculatorDividendCommon());
		map.put(StockType.PREFERRED, calculatorDividendPreferred());
		return Collections.unmodifiableMap(map);
	}
	
	@Bean
	public Calculator<Stock, Double> calculatorDividend() {
		CalculatorDividend calculatorDividend = new CalculatorDividend();
		calculatorDividend.setCalculators(calculatorsDividendMap());
		return calculatorDividend;
	}
	
	@Bean
	public Calculator<StockAndPrice, Double> calculatorDividendYield() {
		CalculatorDividendYield calculatorDividendYield = new CalculatorDividendYield();
		calculatorDividendYield.setCalculatorDividend(calculatorDividend());
		return calculatorDividendYield;
	}
	
	@Bean
	public Calculator<StockAndPrice, Double> calculatorPeRatio() {
		CalculatorPeRatio calculatorPeRatio = new CalculatorPeRatio();
		calculatorPeRatio.setCalculatorDividend(calculatorDividend());
		return calculatorPeRatio;
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
