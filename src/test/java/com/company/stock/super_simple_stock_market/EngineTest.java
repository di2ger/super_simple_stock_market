package com.company.stock.super_simple_stock_market;

import java.util.Date;

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
import com.company.stock.super_simple_stock_market.engine.data_types.StockAndCollectionOfTradesAndInterval;
import com.company.stock.super_simple_stock_market.engine.data_types.StockAndPrice;
import com.company.stock.super_simple_stock_market.model.Stock;
import com.company.stock.super_simple_stock_market.model.StockCommon;
import com.company.stock.super_simple_stock_market.model.Trade;

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
	public void calculatePeRatioTest() throws ConfigurationException {
		StockAndPrice input = new StockAndPrice();
		input.setPrice(100);
		Stock stock = new StockCommon();
		stock.setLastDividend(10);
		input.setStock(stock);
		ResultData<Double> result = engine.calculatePeRatio(input);
		Assert.assertNull("Error description is not empty: " + result.getErrorDescription(),
				result.getErrorDescription());
		Assert.assertEquals("Result is wrong", 100d/10, result.getResult(), 1e-32);
	}
	
	@Test
	public void calculateVolumeWeightedStockPriceTest() throws ConfigurationException {
		StockAndCollectionOfTradesAndInterval input = new StockAndCollectionOfTradesAndInterval();
		input.setInterval(1);
		Stock stock = new StockCommon();
		input.setStock(stock);
		Trade t1 = new Trade();
		t1.setPrice(100);
		t1.setQuantity(2);
		t1.setTimestamp(new Date());
		t1.setStock(stock);
		engine.getTrades().add(t1);
		Trade t2 = new Trade();
		t2.setPrice(1);
		t2.setQuantity(98);
		t2.setTimestamp(new Date());
		t2.setStock(stock);
		engine.getTrades().add(t2);
		input.setTrades(engine.getTrades());

		ResultData<Double> result = engine.calculateVolumeWeightedStockPrice(input);
		Assert.assertNull("Error description is not empty: " + result.getErrorDescription(),
				result.getErrorDescription());
		Assert.assertEquals("Result is wrong", 2.98, result.getResult(), 1e-32);
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
