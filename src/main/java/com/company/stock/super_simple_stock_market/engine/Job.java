package com.company.stock.super_simple_stock_market.engine;

public class Job<T> {
	private CalculationType calculationType;
	private T inputData;
	
	public CalculationType getCalculationType() {
		return calculationType;
	}
	public void setCalculationType(CalculationType calculationType) {
		this.calculationType = calculationType;
	}
	public T getInputData() {
		return inputData;
	}
	public void setInputData(T inputData) {
		this.inputData = inputData;
	}
	
}
