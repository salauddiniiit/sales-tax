package com.points.salesapp.shopping;

import java.math.BigDecimal;
import java.util.Map;

import com.points.salesapp.factory.TaxStrategyFactory;
import com.points.salesapp.goods.Good;
import com.points.salesapp.taxes.strategy.TaxDutiesStrategy;

/*
 * the goal of this object provide the means to manipulate the items 
 * being purchased and to delegate the calculation of the goods cost
 * 
 */

public interface ShoppingCart {
	
	public String getCountry();
	public void setCountry(String aCountry);
	public TaxStrategyFactory getTaxesFactory();
	public void setTaxesFactory(TaxStrategyFactory taxesFactory);
	public TaxDutiesStrategy getTaxStrategy();
	public void setTaxStrategy();
	public Map<String,ShoppingItem> getItems();
	public void addShoppingItem(Good saleGood);
	public ShoppingItem getShoppingItem(Good saleGood);
	public void clearShoppingCart();
	public void printReceipt();
	public BigDecimal getTotalAmount();
	public BigDecimal getTotalTaxes();
	public BigDecimal getGrandTotal();

}
