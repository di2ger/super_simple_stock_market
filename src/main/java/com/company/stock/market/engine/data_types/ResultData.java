package com.company.stock.market.engine.data_types;

public class ResultData<T> {
	private T result;
	private String errorDescription;
	
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
}
