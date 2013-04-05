package com.points.salesapp.taxes.strategy.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.points.salesapp.goods.Good;
import com.points.salesapp.shopping.ShoppingItem;
import com.points.salesapp.taxes.codes.Taxes;
import com.points.salesapp.taxes.strategy.TaxDutiesStrategy;

/*
 * this object has a group of taxes that can be applied to goods and the business logic to apply them
 * This is the specific tax implementation associated with the country US (7% Tax)
 */

public class AmericanTaxDutiesStrategyImpl implements TaxDutiesStrategy{

	public static BigDecimal BASIC_TAX = new BigDecimal(0.07).setScale(2,RoundingMode.HALF_EVEN);
	
	private List<Taxes> taxRates;

	public AmericanTaxDutiesStrategyImpl() {
		setupCustomTaxRates();
	}
	
	/*
	 * This method provides the business logic to determine the taxes applied to goods
	 */	
	@Override
	public void setTaxes(ShoppingItem item) {
		Good saleGood = item.getSaleGood();
		item.setSaleTaxes(BigDecimal.ZERO);
		BigDecimal finalTaxRate = BASIC_TAX;
		for (Taxes tax : taxRates) {
			if (saleGood.getTaxcode().equals(tax.getTaxCode())) {
				finalTaxRate = tax.getValue();
			}
		}
		BigDecimal roundedTaxes = 
			new BigDecimal(
					Math.ceil(item.getSalePrice().multiply(finalTaxRate).doubleValue() * 20) / 20)
					.setScale(2, RoundingMode.HALF_UP);
		item.setSaleTaxes(roundedTaxes);
	}

	@Override
	public void setTaxes(Collection<ShoppingItem> items) {
		for (ShoppingItem shoppingItem : items) {
			setTaxes(shoppingItem);
		}
	}
	
	private void setupCustomTaxRates() {
		taxRates = new ArrayList<Taxes>();
	}
	
	@Override
	public String toString() {
		return "Basic Tax:" + BASIC_TAX + taxRates.toString();
	}
}
