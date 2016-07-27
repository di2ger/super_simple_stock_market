package com.company.stock.market.engine.calculators;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.company.stock.market.api.model.ResultData;
import com.company.stock.market.api.model.Stock;
import com.company.stock.market.engine.data.wrappers.CollectionOfTrades;
import com.company.stock.market.engine.data.wrappers.RunningSums;

public class CalculatorGbceAllShareIndex extends Calculator<CollectionOfTrades, Double> {

	@Override
	public ResultData<Double> calculate(CollectionOfTrades inputData) {
		
		ConcurrentMap<Stock, RunningSums> map = new ConcurrentHashMap<>();
		throwIfZero(inputData.getTrades().size(), "Trades count");
		inputData.getTrades()
				.parallelStream()
				.forEach(trade -> {
					throwIfNull(trade.getStock(), "Stock");
					throwIfNotPositive(trade.getQuantity(), "Quantity");
					throwIfNotPositive(trade.getPrice(), "Price");
					RunningSums newRunningSums = new RunningSums();
					RunningSums runningSums = map.putIfAbsent(trade.getStock(), newRunningSums);
					if (runningSums == null) {
						runningSums = newRunningSums;
					}
					runningSums.getRunningSumPriceByQuantity().addAndGet(trade.getPrice()*trade.getQuantity());
					runningSums.getRunningSumQuantity().addAndGet(trade.getQuantity());
				});

		double multiplication = map.values()
				.parallelStream()
				.peek(runningSums -> {
					// In current implementation it should newer throw error, but implementation might change
					throwIfZero(runningSums.getRunningSumQuantity().get(), "Quantity sum");
				})
				.mapToDouble(runningSums -> 1d*runningSums.getRunningSumPriceByQuantity().get()
						/runningSums.getRunningSumQuantity().get())
				.reduce(1, (a, b) -> a*b);

		ResultData<Double> res = new ResultData<>();
		res.setResult(Math.pow(multiplication, 1/map.size()));
		return res;
	}

}
