package com.points.salesapp.factory.impl;

import com.points.salesapp.factory.TaxStrategyFactory;
import com.points.salesapp.taxes.strategy.TaxDutiesStrategy;
import com.points.salesapp.taxes.strategy.impl.AmericanTaxDutiesStrategyImpl;
import com.points.salesapp.taxes.strategy.impl.DefaultTaxDutiesStrategyImpl;

/*
 * this factory is used to provide the ability to have multiple 
 * tax strategies based on specific parameters (ie. country) 
 */

public class TaxStrategyFactoryImpl implements TaxStrategyFactory {

	public static String CANADA = "CA";
	public static String USA = "US";

	private String country;

	public TaxStrategyFactoryImpl( String country) {
		this.country = country;
	}
	
	@Override
	public String getCountry() {
		return country;
	}

	@Override
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public TaxDutiesStrategy getTaxesStrategy() {
		if (country.equals(USA)) {
			return new AmericanTaxDutiesStrategyImpl();
		} else {
			return new DefaultTaxDutiesStrategyImpl();
		}
	}

	@Override
	public String toString() {
		return "Strategy: " + (country.equals(USA)?"AmericanTaxDutiesStrategy":"DefaultTaxDutiesStrategy");
	}
}
