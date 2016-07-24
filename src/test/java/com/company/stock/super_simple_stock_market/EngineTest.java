package com.company.stock.super_simple_stock_market;

import javax.naming.ConfigurationException;

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

import com.company.stock.super_simple_stock_market.engine.Engine;
import com.company.stock.super_simple_stock_market.engine.data_types.ResultData;
import com.company.stock.super_simple_stock_market.engine.data_types.StockAndPrice;
import com.company.stock.super_simple_stock_market.model.Stock;
import com.company.stock.super_simple_stock_market.model.StockCommon;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
// This line is only to avoid SpringJUnit4ClassRunner extra logs
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})

public class EngineTest {
	@Autowired
	private Engine engine;
	
	@Test
	public void calculateDividendYieldTest() throws ConfigurationException {
		StockAndPrice input = new StockAndPrice();
		input.setPrice(100);
		Stock stock = new StockCommon();
		stock.setLastDividend(10);
		input.setStock(stock);
		ResultData<Double> result = engine.calculateDividendYield(input);
		Assert.assertNull("Error description is not empty: " + result.getErrorDescription(),
				result.getErrorDescription());
		Assert.assertEquals("Result is wrong", 10d/100, result.getResult(), 1e-32);
	}
	
	@Test
	public void allCalculatorsAreSet() {
		Assert.assertNotNull("CalculatorDividendYield is not configured", engine.getCalculatorDividendYield());
		Assert.assertNotNull("CalculatorPeRatio is not configured", engine.getCalculatorPeRatio());
		Assert.assertNotNull("CalculatorVolumeWeightedStockPrice is not configured",
				engine.getCalculatorVolumeWeightedStockPrice());
		Assert.assertNotNull("CalculatorGbceAllShareIndex is not configured", engine.getCalculatorGbceAllShareIndex());
	}
	

}
