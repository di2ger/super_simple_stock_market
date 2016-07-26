package com.company.stock.market.model;

public abstract class Stock {
	private StockSymbol symbol;
	// in pennies
	private Integer lastDividend;
	// in pennies
	private Integer parValue;
	
	public abstract StockType getType();
	
	public StockSymbol getSymbol() {
		return symbol;
	}
	public void setSymbol(StockSymbol symbol) {
		this.symbol = symbol;
	}
	public Integer getLastDividend() {
		return lastDividend;
	}
	public void setLastDividend(Integer lastDividend) {
		this.lastDividend = lastDividend;
	}
	public Integer getParValue() {
		return parValue;
	}
	public void setParValue(Integer parValue) {
		this.parValue = parValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parValue == null) ? 0 : parValue.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (parValue == null) {
			if (other.parValue != null)
				return false;
		} else if (!parValue.equals(other.parValue))
			return false;
		if (symbol != other.symbol)
			return false;
		return true;
	}
	
	

}
