package com.points.salesapp.taxes.strategy.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.points.salesapp.goods.Good;
import com.points.salesapp.shopping.ShoppingItem;
import com.points.salesapp.taxes.TaxCodes;
import com.points.salesapp.taxes.codes.Taxes;
import com.points.salesapp.taxes.codes.impl.TaxesImpl;
import com.points.salesapp.taxes.strategy.TaxDutiesStrategy;

/*
 * this object has a group of taxes that can be applied to goods and the business logic to apply them
 * This is the specific tax implementation associated with the country CA ( 10% Basic Tax )
 */

public class DefaultTaxDutiesStrategyImpl implements TaxDutiesStrategy{

	public static BigDecimal BASIC_TAX = new BigDecimal(0.10).setScale(2,RoundingMode.HALF_EVEN);
	public static BigDecimal IMPORT_TAX = new BigDecimal(0.05).setScale(2,RoundingMode.HALF_EVEN);
	
	private List<Taxes> taxRates;

	public DefaultTaxDutiesStrategyImpl() {
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
		if (saleGood.isImported()) {
			finalTaxRate = finalTaxRate.add(IMPORT_TAX);
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
		taxRates.add(new TaxesImpl(TaxCodes.BOOKS, BigDecimal.ZERO));
		taxRates.add(new TaxesImpl(TaxCodes.FOODS, BigDecimal.ZERO));
		taxRates.add(new TaxesImpl(TaxCodes.MEDICAL, BigDecimal.ZERO));
	}
	
	public String toString() {
		return "Basic Tax:" + BASIC_TAX
		+ "Import Tax:" + IMPORT_TAX
		+ taxRates.toString();
	}
}
