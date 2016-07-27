package com.company.stock.market.api.service;

import java.util.Collection;
import java.util.List;

import com.company.stock.market.api.model.ResultData;
import com.company.stock.market.api.model.Stock;
import com.company.stock.market.api.model.Trade;

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
