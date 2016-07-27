package com.company.stock.market;

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

import com.company.stock.market.api.model.ResultData;
import com.company.stock.market.api.model.Stock;
import com.company.stock.market.api.model.StockCommon;
import com.company.stock.market.api.model.StockPreferred;
import com.company.stock.market.api.model.Trade;
import com.company.stock.market.api.service.Engine;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
// This line is only to avoid SpringJUnit4ClassRunner extra logs
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})

public class EngineTest {
	@Autowired
	private Engine engine;
	
	@Test
	public void calculateDividendYieldCommonTest() throws ConfigurationException {
		Stock stock = new StockCommon();
		stock.setLastDividend(10);
		ResultData<Double> result = engine.calculateDividendYield(stock, 100);
		Assert.assertNull("Error description is not empty: " + result.getErrorDescription(),
				result.getErrorDescription());
		Assert.assertEquals("Result is wrong", 10d/100, result.getResult(), 1e-32);
	}
	
	@Test
	public void calculateDividendYieldPreferredTest() throws ConfigurationException {
		StockPreferred stock = new StockPreferred();
		stock.setFixedDividend(10);
		stock.setParValue(50);
		ResultData<Double> result = engine.calculateDividendYield(stock, 100);
		Assert.assertNull("Error description is not empty: " + result.getErrorDescription(),
				result.getErrorDescription());
		Assert.assertEquals("Result is wrong", 5d/100, result.getResult(), 1e-32);
	}
	
	@Test
	public void calculatePeRatioCommonTest() throws ConfigurationException {
		Stock stock = new StockCommon();
		stock.setLastDividend(10);
		ResultData<Double> result = engine.calculatePeRatio(stock, 100);
		Assert.assertNull("Error description is not empty: " + result.getErrorDescription(),
				result.getErrorDescription());
		Assert.assertEquals("Result is wrong", 100d/10, result.getResult(), 1e-32);
	}
	
	@Test
	public void calculatePeRatioPreferredTest() throws ConfigurationException {
		StockPreferred stock = new StockPreferred();
		stock.setFixedDividend(10);
		stock.setParValue(200);
		ResultData<Double> result = engine.calculatePeRatio(stock, 100);
		Assert.assertNull("Error description is not empty: " + result.getErrorDescription(),
				result.getErrorDescription());
		Assert.assertEquals("Result is wrong", 100d/20, result.getResult(), 1e-32);
	}
	
	@Test
	public void calculateVolumeWeightedStockPriceTest() throws ConfigurationException {
		Stock stock = new StockCommon();
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

		ResultData<Double> result = engine.calculateVolumeWeightedStockPrice(stock, engine.getTrades(), 1);
		Assert.assertNull("Error description is not empty: " + result.getErrorDescription(),
				result.getErrorDescription());
		Assert.assertEquals("Result is wrong", 2.98, result.getResult(), 1e-32);
	}
	
	@Test
	public void calculateGbceAllShareIndexTest() {
		Stock stock1 = new StockCommon();
		Stock stock2 = new StockPreferred();
		for (int i = 0; i < 100; i++) {
			Trade trade = new Trade();
			if (i % 2 == 0) {
				trade.setStock(stock1);
				trade.setQuantity(1);
				trade.setPrice(100);
			} else {
				trade.setStock(stock2);
				trade.setQuantity(2);
				trade.setPrice(200);
			}
			engine.getTrades().add(trade);
		}
		Date start = new Date();
		ResultData<Double> result = engine.calculateGbceAllShareIndex(engine.getTrades());
		Date finish = new Date();
		System.out.println("Duration is " + (finish.getTime() - start.getTime()) + " ms");
		Assert.assertNull("Error description is not empty: " + result.getErrorDescription(),
				result.getErrorDescription());
		Assert.assertEquals("Result is wrong", 1.0, result.getResult(), 1e-32);
	}
}
