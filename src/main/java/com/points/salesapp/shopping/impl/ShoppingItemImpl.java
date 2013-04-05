package com.points.salesapp.shopping.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.points.salesapp.goods.Good;
import com.points.salesapp.shopping.ShoppingItem;

/*
 * this object contains details about the good being purchased 
 * as well as to provide a snapshot of costs associated with it 
 */

public class ShoppingItemImpl implements ShoppingItem {

	private Good 		saleGood;
	private BigDecimal	salePrice;
	private BigDecimal	saleTaxes;
	private int			qty;
	
	public ShoppingItemImpl(Good saleGood) {
		setSaleGood(saleGood);
	}

	@Override
	public Good getSaleGood() {
		return saleGood;
	}

	@Override
	public void setSaleGood(Good saleGood) {
		this.saleGood = saleGood;
		this.qty = 1;
		setSalePrice(saleGood.getPrice());
		setSaleTaxes(BigDecimal.ZERO);
	}

	@Override
	public BigDecimal getSalePrice() {
		return salePrice.setScale(2,RoundingMode.HALF_EVEN);
	}

	@Override
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice.multiply(new BigDecimal(qty));
	}

	@Override
	public BigDecimal getSaleTaxes() {
		return saleTaxes.setScale(2,RoundingMode.HALF_EVEN);
	}

	@Override
	public void setSaleTaxes(BigDecimal saleTaxes) {
		this.saleTaxes = saleTaxes;
	}

	@Override
	public BigDecimal getTotalPrice() {
		return salePrice.add(saleTaxes).setScale(2,RoundingMode.HALF_EVEN);
	}

	@Override
	public int getQty() {
		return qty;
	}

	@Override
	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public void increaseQty() {
		this.qty++;
		setSalePrice(saleGood.getPrice());
	}
	
	@Override
	public String toString() {
		return "ShoppingItem: " + saleGood.toString() + " Price:" + getSalePrice() + "Taxes:" + getSaleTaxes();
	}
}
