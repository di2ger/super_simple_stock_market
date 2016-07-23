package com.company.stock.super_simple_stock_market;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.ConfigurationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.company.stock.super_simple_stock_market.engine.CalculationType;
import com.company.stock.super_simple_stock_market.engine.Engine;
import com.company.stock.super_simple_stock_market.engine.data_types.ResultData;
import com.company.stock.super_simple_stock_market.engine.data_types.StockAndPrice;
import com.company.stock.super_simple_stock_market.model.Stock;
import com.company.stock.super_simple_stock_market.model.StockCommon;

import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
// This line is only to avoid SpringJUnit4ClassRunner extra logs
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})

public class EngineTest {
	@Autowired
	private Engine engine;
	
	@Test
	public void calculateTest() throws ConfigurationException {
		StockAndPrice input = new StockAndPrice();
		input.setPrice(100);
		Stock stock = new StockCommon();
		stock.setLastDividend(10);
		input.setStock(stock);
		ResultData<Double> result = engine.calculate(CalculationType.DIVIDEND_YIELD, input);
		Assert.assertNull("Error description is not empty: " + result.getErrorDescription(),
				result.getErrorDescription());
		Assert.assertEquals("Result is wrong", 10d/100, result.getResult(), 1e-32);
	}
	
	@Test
	public void allCalculatorsAreSet() {
		// Arrays.asList is wrapped in ArrayList because it returns array without removeAll method support
		List<CalculationType> calculationTypes = new ArrayList<>(Arrays.asList(CalculationType.values()));
		calculationTypes.removeAll(engine.getCalculators().keySet());
		Assert.assertTrue("The following calculation types are not configured: " + calculationTypes,
				calculationTypes.isEmpty());
	}
	

}
