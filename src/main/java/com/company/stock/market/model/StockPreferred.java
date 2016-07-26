package com.company.stock.market.model;

public class StockPreferred extends Stock {
	// in percent
	private double fixedDividend;
	
	@Override
	public StockType getType() {
		return StockType.PREFERRED;
	}

	public double getFixedDividend() {
		return fixedDividend;
	}
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(fixedDividend);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (Double.doubleToLongBits(fixedDividend) != Double.doubleToLongBits(other.fixedDividend))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StockPreferred [fixedDividend=" + fixedDividend + ", " + super.toString() + "]";
	}

	
	
	
}
