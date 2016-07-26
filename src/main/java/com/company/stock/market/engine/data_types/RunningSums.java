package com.company.stock.market.engine.data_types;

import java.util.concurrent.atomic.AtomicLong;

public class RunningSums {
	private final AtomicLong runningSumPriceByQuantity = new AtomicLong(0);
	private final AtomicLong runningSumQuantity = new AtomicLong(0);
	
	public AtomicLong getRunningSumPriceByQuantity() {
		return runningSumPriceByQuantity;
	}
	public AtomicLong getRunningSumQuantity() {
		return runningSumQuantity;
	}
	
	
}
