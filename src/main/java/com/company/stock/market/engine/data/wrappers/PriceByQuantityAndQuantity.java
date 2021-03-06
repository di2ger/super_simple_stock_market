package com.company.stock.market.engine.data.wrappers;

public class PriceByQuantityAndQuantity {
	private long priceByQuantity;
	private long quantity;
	
	public PriceByQuantityAndQuantity(long priceByQuantity, long quantity) {
		this.priceByQuantity = priceByQuantity;
		this.quantity = quantity;
	}
	
	public long getPriceByQuantity() {
		return priceByQuantity;
	}
	public void setPriceByQuantity(long priceByQuantity) {
		this.priceByQuantity = priceByQuantity;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

}
