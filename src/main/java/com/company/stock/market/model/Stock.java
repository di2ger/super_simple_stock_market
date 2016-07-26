package com.company.stock.market.model;

public abstract class Stock {
	private String symbol;
	// in pennies
	private long lastDividend;
	// in pennies
	private long parValue;
	
	public abstract StockType getType();
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public long getLastDividend() {
		return lastDividend;
	}
	public void setLastDividend(long lastDividend) {
		this.lastDividend = lastDividend;
	}
	public long getParValue() {
		return parValue;
	}
	public void setParValue(long parValue) {
		this.parValue = parValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (parValue ^ (parValue >>> 32));
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
		if (parValue != other.parValue)
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", lastDividend=" + lastDividend + ", parValue=" + parValue + "]";
	}
	
	
}
