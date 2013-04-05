package com.points.salesapp.shopping.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;

import com.points.salesapp.factory.TaxStrategyFactory;
import com.points.salesapp.factory.impl.TaxStrategyFactoryImpl;
import com.points.salesapp.goods.Good;
import com.points.salesapp.shopping.ShoppingCart;
import com.points.salesapp.shopping.ShoppingItem;
import com.points.salesapp.taxes.strategy.TaxDutiesStrategy;

/*
 * the goal of this object provide the means to manipulate the items 
 * being purchased and to delegate the calculation of the goods cost
 * 
 */

public class ShoppingCartImpl implements ShoppingCart {
	
	private String 						country;
	private TaxStrategyFactory 			taxesFactory;
	private TaxDutiesStrategy 			taxStrategy;
		
	private Map<String,ShoppingItem> 	items;
	
	private BigDecimal	totalAmount;
	private BigDecimal	totalTaxes;
	
	public ShoppingCartImpl(String country) {
		setCountry(country);
	}

	@Override
	public String getCountry() {
		return country;
	}
	
	@Override
	public void setCountry(String aCountry) {
		country = aCountry;
		getTaxesFactory().setCountry(aCountry);
		setTaxStrategy();
	}

	@Override
	public TaxStrategyFactory getTaxesFactory() {
		if (null == taxesFactory) {
			setTaxesFactory(new TaxStrategyFactoryImpl(country));
		}
		return taxesFactory;
	}
	
	@Override
	public void setTaxesFactory(TaxStrategyFactory taxesFactory) {
		this.taxesFactory = taxesFactory;
		setTaxStrategy();
	}
	
	@Override
	public TaxDutiesStrategy getTaxStrategy() {
		if (null == taxStrategy) {
			setTaxStrategy();
		}
		return taxStrategy;
	}
	
	@Override
	public void setTaxStrategy() {
		this.taxStrategy = taxesFactory.getTaxesStrategy();
		getTaxStrategy().setTaxes(getItems().values());
		calculateShoppingCartTotals();
	}
	
	@Override
	public Map<String,ShoppingItem> getItems() {
		if (null == items) {
			items = new LinkedHashMap<String,ShoppingItem>();
		}
		return items;
	}
	
	@Override
	public void addShoppingItem(Good saleGood) {
		ShoppingItem newItem = getItems().get(saleGood.getUniqueId());  
		if (newItem == null) {
			newItem = new ShoppingItemImpl(saleGood);
			getItems().put(saleGood.getUniqueId(),newItem);
		} else {
			newItem.increaseQty();
		}
		getTaxStrategy().setTaxes(newItem);
		calculateShoppingCartTotals();
	}
	
	public ShoppingItem getShoppingItem(Good saleGood) {
		return getItems().get(saleGood.getUniqueId());
	}

	@Override
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	@Override
	public BigDecimal getTotalTaxes() {
		return totalTaxes;
	}

	@Override
	public BigDecimal getGrandTotal() {
		return totalAmount.add(totalTaxes);
	}

	@Override
	public void clearShoppingCart() {
		totalAmount = BigDecimal.ZERO;
		totalTaxes = BigDecimal.ZERO;
		items=null;
	}
		
	private void calculateShoppingCartTotals() {
		totalAmount = BigDecimal.ZERO;
		totalTaxes = BigDecimal.ZERO;
		for (ShoppingItem shoppingItem : getItems().values()) {
			totalAmount = totalAmount.add(shoppingItem.getSalePrice());
			totalTaxes = totalTaxes.add(shoppingItem.getSaleTaxes());
		}
	}
	
	@Override
	public void printReceipt() {
		System.out.println("============ Receipt =================");
		for (ShoppingItem shoppingItem : getItems().values()) {
			System.out.println(
					"1 " +  (shoppingItem.getSaleGood().isImported()?"imported ":"")
					+ shoppingItem.getSaleGood().getName() 
					+ " : " + shoppingItem.getTotalPrice().setScale(2,RoundingMode.HALF_EVEN)
					);
		}
		System.out.println("\nSales Taxes:" + totalTaxes.setScale(2,RoundingMode.HALF_EVEN));
		System.out.println("Total:" + getGrandTotal().setScale(2,RoundingMode.HALF_EVEN)+"\n");
	}
}
