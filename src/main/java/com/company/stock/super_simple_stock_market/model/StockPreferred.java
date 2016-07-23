package com.company.stock.super_simple_stock_market.model;

public class StockPreferred extends Stock {
	// in percent
	private Double fixedDividend;
	
	@Override
	public StockType getType() {
		return StockType.PREFERRED;
	}

	public Double getFixedDividend() {
		return fixedDividend;
	}
	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fixedDividend == null) ? 0 : fixedDividend.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockPreferred other = (StockPreferred) obj;
		if (fixedDividend == null) {
			if (other.fixedDividend != null)
				return false;
		} else if (!fixedDividend.equals(other.fixedDividend))
			return false;
		return true;
	}
	
}
