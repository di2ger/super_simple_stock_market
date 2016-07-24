package com.company.stock.super_simple_stock_market.engine.calculators;

import com.company.stock.super_simple_stock_market.engine.data_types.StockAndCollectionOfTradesAndInterval;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.company.stock.super_simple_stock_market.engine.data_types.ResultData;

public class CalculatorVolumeWeightedStockPrice extends Calculator<StockAndCollectionOfTradesAndInterval, Double> {

	@Override
	public ResultData<Double> calculate(StockAndCollectionOfTradesAndInterval inputData) {
		if (inputData.getInterval() == null) {
			throw new IllegalArgumentException("Interval is not set");
		}
		
		AtomicLong runningSumPriceByQuantity = new AtomicLong(0);
		AtomicLong runningSumQuantity = new AtomicLong(0);
		
		Date currentTime = new Date();
		
		inputData.getTrades()
				.parallelStream()
				.filter(trade -> trade.getStock() != null
						&& trade.getStock().equals(inputData.getStock()))
				.filter(trade -> trade.getTimestamp() != null 
						&& datesDiffInSeconds(trade.getTimestamp(), currentTime) <= inputData.getInterval())
				.forEach(trade -> {
					runningSumPriceByQuantity.addAndGet(trade.getPrice()*trade.getQuantity());
					runningSumQuantity.addAndGet(trade.getQuantity());
				});
		if (runningSumQuantity.get() == 0) {
			throw new IllegalArgumentException("Zero quantity");
		}
		ResultData<Double> res = new ResultData<>();
		res.setResult(1d*runningSumPriceByQuantity.get()/runningSumQuantity.get());
		return res;
	}
	
	private long datesDiffInSeconds(Date from, Date to) {
		return TimeUnit.MILLISECONDS.toSeconds(to.getTime() - from.getTime());
	}

}
