package com.company.stock.market.engine.calculators;

import java.util.function.Function;

import com.company.stock.market.engine.data_types.ResultData;

public abstract class Calculator<I, O> implements Function<I, ResultData<O>> {

	public ResultData<O> apply(I inputData) {
		ResultData<O> r;
		try {
			r = calculate(inputData);
		} catch (Exception e) {
			r = new ResultData<O>();
			r.setErrorDescription(e.toString());
			System.out.println("Calculation error: " + e);
			e.printStackTrace();
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
		throwIfZero(new Long(valueToCheck), valueName);
	}
	
	public void throwIfZero(Long valueToCheck, String valueName) {
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
	
	public void throwIfNotPositive(long valueToCheck, String valueName) {
		if (valueToCheck <= 0) {
			throw new IllegalArgumentException(valueName + " is not positive");
		}
	}
}
