package com.company.stock.market.model;

import java.util.Collection;
import java.util.List;

public interface Engine {
	// price in pennies
	public ResultData<Double> calculateDividendYield (Stock stock, long price);
	
	// price in pennies
	public ResultData<Double> calculatePeRatio (Stock stock, long price);
	
	// interval in seconds
	public ResultData<Double> calculateVolumeWeightedStockPrice (Stock stock, Collection<Trade> trades, int interval);
	
	public ResultData<Double> calculateGbceAllShareIndex (Collection<Trade> trades);
	
	public List<Trade> getTrades();

}
