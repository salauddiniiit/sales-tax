package com.points.salesapp.factory;

import com.points.salesapp.taxes.strategy.TaxDutiesStrategy;

/*
 * this factory is used to provide the ability to have multiple 
 * tax strategies based on specific parameters (ie. country) 
 */

public interface TaxStrategyFactory {

	public String getCountry();
	public void setCountry(String country);
	public TaxDutiesStrategy getTaxesStrategy();

}
