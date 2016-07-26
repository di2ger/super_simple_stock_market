package com.company.stock.market;

import java.util.EnumSet;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.company.stock.market.engine.EngineImpl;
import com.company.stock.market.engine.calculators.Calculator;
import com.company.stock.market.engine.calculators.CalculatorDividend;
import com.company.stock.market.engine.calculators.CalculatorPeRatio;
import com.company.stock.market.engine.data.wrappers.StockAndPrice;
import com.company.stock.market.model.Engine;
import com.company.stock.market.model.Stock;
import com.company.stock.market.model.StockType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
// This line is only to avoid SpringJUnit4ClassRunner extra logs
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})

public class ConfigTest {
	@Autowired
	private Engine engine;
	
	@Autowired
	CalculatorDividend calculatorDividend;
	
	@Test
	public void allCalculatorsAreSet() {
		EngineImpl engineImpl = (EngineImpl)engine;
		Assert.assertNotNull("CalculatorDividendYield is not configured", engineImpl.getCalculatorDividendYield());
		Assert.assertNotNull("CalculatorPeRatio is not configured", engineImpl.getCalculatorPeRatio());
		Assert.assertNotNull("CalculatorVolumeWeightedStockPrice is not configured",
				engineImpl.getCalculatorVolumeWeightedStockPrice());
		Assert.assertNotNull("CalculatorGbceAllShareIndex is not configured", 
				engineImpl.getCalculatorGbceAllShareIndex());
	}
	
	@Test
	public void allCalculatorsDividendAreSet() {
		EnumSet<StockType> stockTypes = EnumSet.allOf(StockType.class);
		Map<StockType, Calculator<Stock, Double>> calculatorsMap = calculatorDividend.getCalculators();
		stockTypes.removeAll(calculatorsMap.keySet());
		Assert.assertEquals("CalculatorDividend is not configured for stockTypes: " + stockTypes, 0, stockTypes.size());
		for (Entry<StockType, Calculator<Stock, Double>> calculator : calculatorsMap.entrySet()) {
			Assert.assertNotNull("CalculatorDividend is null for " + calculator.getKey(), calculator.getValue());
		}
	}
	

}
