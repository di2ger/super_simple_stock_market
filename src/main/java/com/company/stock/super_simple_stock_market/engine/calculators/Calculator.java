package com.company.stock.super_simple_stock_market.engine.calculators;

import java.util.concurrent.ExecutorService;
import java.util.function.Function;

import com.company.stock.super_simple_stock_market.engine.data_types.ResultData;

public abstract class Calculator<I, O> implements Function<I, ResultData<O>> {
	private ExecutorService executorService;

	public ResultData<O> apply(I inputData) {
		ResultData<O> r;
		try {
			r = calculate(inputData);
		} catch (Exception e) {
			r = new ResultData<O>();
			r.setErrorDescription(e.toString());
		}
		return r;
	}
	
	public abstract ResultData<O> calculate(I inputData);
	
	public void throwIfNull(Object valueToCheck, String valueName) {
		if (valueToCheck == null) {
			throw new IllegalArgumentException(valueName + " is missing");
		}
	}
	
	public void throwIfZero(Integer valueToCheck, String valueName) {
		throwIfNull(valueToCheck, valueName);
		if (valueToCheck == 0) {
			throw new IllegalArgumentException(valueName + " is zero");
		}
	}
	
	public void throwIfZero(Double valueToCheck, String valueName) {
		throwIfNull(valueToCheck, valueName);
		if (Math.abs(valueToCheck) < Double.MIN_NORMAL) {
			throw new IllegalArgumentException(valueName + " is zero");
		}
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

}