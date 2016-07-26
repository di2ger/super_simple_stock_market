package com.company.stock.super_simple_stock_market.engine.data_types;

public class PriceByQuantityAndQuantity {
	private Long priceByQuantity;
	private Long quantity;
	
	public PriceByQuantityAndQuantity(Long priceByQuantity, Long quantity) {
		this.priceByQuantity = priceByQuantity;
		this.quantity = quantity;
	}
	
	public Long getPriceByQuantity() {
		return priceByQuantity;
	}
	public void setPriceByQuantity(Long priceByQuantity) {
		this.priceByQuantity = priceByQuantity;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
