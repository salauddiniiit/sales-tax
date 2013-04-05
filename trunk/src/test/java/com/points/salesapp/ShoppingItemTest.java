package com.points.salesapp;

import java.math.BigDecimal;
import java.math.RoundingMode;

import junit.framework.TestCase;

import com.points.salesapp.goods.Good;
import com.points.salesapp.goods.impl.GoodImpl;
import com.points.salesapp.shopping.ShoppingItem;
import com.points.salesapp.shopping.impl.ShoppingItemImpl;

public class ShoppingItemTest extends TestCase {

	/**
	 * 	Scenario: Assign new good to shoppingItem and make sure good values are propagated
	 */
	public void testNewShoppingItem() {
		
		Good newBook = new GoodImpl("bottle of perfume",new BigDecimal(27.99),Good.IMPORTED);
		ShoppingItem shopItem = new ShoppingItemImpl(newBook);
		
		BigDecimal priceCheck = new BigDecimal(27.99).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shopItem.getSalePrice().compareTo(priceCheck));
		assertEquals(0,shopItem.getSaleTaxes().compareTo(BigDecimal.ZERO));
		assertEquals(1,shopItem.getQty());
		assertEquals(0,shopItem.getTotalPrice().compareTo(priceCheck));
	
	}

	/**
	 * 	Scenario: Increase good quantity in shoppingItem and make sure instance properties are updated
	 */
	public void testIncreasedQtyShoppingItem() {
		
		Good newBook = new GoodImpl("bottle of perfume",new BigDecimal(27.99),Good.IMPORTED);
		ShoppingItem shopItem = new ShoppingItemImpl(newBook);
		
		BigDecimal priceCheck = new BigDecimal(55.98).setScale(2,RoundingMode.HALF_EVEN);
		shopItem.increaseQty();
		assertEquals(0,shopItem.getSalePrice().compareTo(priceCheck));
		assertEquals(0,shopItem.getSaleTaxes().compareTo(BigDecimal.ZERO));
		assertEquals(2,shopItem.getQty());
		assertEquals(0,shopItem.getTotalPrice().compareTo(priceCheck));

	}

	/**
	 * 	Scenario: Reassign new good to same shoppingItem and make sure instance property values are reseted
	 */
	public void testReassignedShoppingItem() {
		
		Good newBook = new GoodImpl("bottle of perfume",new BigDecimal(27.99),Good.IMPORTED);
		ShoppingItem shopItem = new ShoppingItemImpl(newBook);
		
		BigDecimal priceCheck = new BigDecimal(27.99).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shopItem.getSalePrice().compareTo(priceCheck));
		assertEquals(0,shopItem.getSaleTaxes().compareTo(BigDecimal.ZERO));
		assertEquals(1,shopItem.getQty());
		assertEquals(0,shopItem.getTotalPrice().compareTo(priceCheck));

		newBook = new GoodImpl("box of chocolates",new BigDecimal(10.00),Good.DOMESTIC);
		
		shopItem.setSaleGood(newBook);
		priceCheck = new BigDecimal(10.00).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shopItem.getSalePrice().compareTo(priceCheck));
		assertEquals(0,shopItem.getSaleTaxes().compareTo(BigDecimal.ZERO));
		assertEquals(1,shopItem.getQty());
		assertEquals(0,shopItem.getTotalPrice().compareTo(priceCheck));
	
	}
}
