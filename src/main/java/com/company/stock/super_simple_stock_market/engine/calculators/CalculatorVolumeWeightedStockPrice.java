package com.company.stock.super_simple_stock_market.engine.calculators;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.company.stock.super_simple_stock_market.engine.data_types.PriceByQuantityAndQuantity;
import com.company.stock.super_simple_stock_market.engine.data_types.ResultData;
import com.company.stock.super_simple_stock_market.engine.data_types.StockAndCollectionOfTradesAndInterval;

public class CalculatorVolumeWeightedStockPrice extends Calculator<StockAndCollectionOfTradesAndInterval, Double> {

	@Override
	public ResultData<Double> calculate(StockAndCollectionOfTradesAndInterval inputData) {
		if (inputData.getInterval() == null) {
			throw new IllegalArgumentException("Interval is not set");
		}
		
		Date currentTime = new Date();
		
		PriceByQuantityAndQuantity p = inputData.getTrades()
				.parallelStream()
				.filter(trade -> trade.getStock() != null
						&& trade.getStock().equals(inputData.getStock()))
				.filter(trade -> trade.getTimestamp() != null 
						&& datesDiffInSeconds(trade.getTimestamp(), currentTime) <= inputData.getInterval())
				.map(trade -> new PriceByQuantityAndQuantity(1L*trade.getPrice()*trade.getQuantity(),
						1L*trade.getQuantity()))
				.reduce(new PriceByQuantityAndQuantity(0L, 0L), (a, b) -> 
						new PriceByQuantityAndQuantity(a.getPriceByQuantity() + b.getPriceByQuantity(),
						a.getQuantity() + b.getQuantity()));

		throwIfZero(p.getQuantity(), "Quantity sum");
		ResultData<Double> res = new ResultData<>();
		res.setResult(1d*p.getPriceByQuantity()/p.getQuantity());
		return res;
	}
	
	private long datesDiffInSeconds(Date from, Date to) {
		return TimeUnit.MILLISECONDS.toSeconds(to.getTime() - from.getTime());
	}

}
