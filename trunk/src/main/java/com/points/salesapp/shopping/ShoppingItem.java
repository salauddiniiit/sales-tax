package com.points.salesapp.shopping;

import java.math.BigDecimal;

import com.points.salesapp.goods.Good;

/*
 * this object contains details about the good being purchased 
 * as well as to provide a snapshot of costs associated with it 
 */

public interface ShoppingItem {

	public Good getSaleGood();
	public void setSaleGood(Good saleGood);
	public BigDecimal getSalePrice();
	public void setSalePrice(BigDecimal salePrice);
	public BigDecimal getSaleTaxes();
	public void setSaleTaxes(BigDecimal saleTaxes);
	public BigDecimal getTotalPrice();
	public int getQty();
	public void setQty(int qty);
	public void increaseQty();
	
}
