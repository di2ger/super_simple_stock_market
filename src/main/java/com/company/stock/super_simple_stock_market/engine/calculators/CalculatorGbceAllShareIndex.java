package com.company.stock.super_simple_stock_market.engine.calculators;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAdder;

import com.company.stock.super_simple_stock_market.engine.data_types.CollectionOfTrades;
import com.company.stock.super_simple_stock_market.engine.data_types.ResultData;
import com.company.stock.super_simple_stock_market.engine.data_types.RunningSums;
import com.company.stock.super_simple_stock_market.model.Stock;

public class CalculatorGbceAllShareIndex extends Calculator<CollectionOfTrades, Double> {

	@Override
	public ResultData<Double> calculate(CollectionOfTrades inputData) {
		
		ConcurrentMap<Stock, RunningSums> map = new ConcurrentHashMap<>();
		
		inputData.getTrades()
				.parallelStream()
				.filter(trade -> trade.getStock() != null)
				.forEach(trade -> {
					RunningSums runningSums = map.putIfAbsent(trade.getStock(), new RunningSums());
					runningSums.getRunningSumPriceByQuantity().addAndGet(trade.getPrice()*trade.getQuantity());
					runningSums.getRunningSumQuantity().addAndGet(trade.getQuantity());
				});
		
		DoubleAdder runningSum = new DoubleAdder();
		AtomicLong count = new AtomicLong(0);
		map.values()
				.parallelStream()
				.filter(runningSums -> runningSums.getRunningSumQuantity().get() != 0)
				.forEach(runningSums -> {
					Double p = 1d*runningSums.getRunningSumPriceByQuantity().get()/runningSums.getRunningSumQuantity().get();
					runningSum.add(Math.log10(Math.abs(p)));
					count.incrementAndGet();
				});

		ResultData<Double> res = new ResultData<>();
		res.setResult(Math.pow(10, runningSum.doubleValue()/count.get()));
		return res;
	}

}
