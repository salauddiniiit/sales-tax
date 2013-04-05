package com.points.salesapp;

import java.math.BigDecimal;
import java.math.RoundingMode;

import junit.framework.TestCase;

import com.points.salesapp.goods.Good;
import com.points.salesapp.goods.impl.GoodImpl;
import com.points.salesapp.shopping.ShoppingCart;
import com.points.salesapp.shopping.impl.ShoppingCartImpl;
import com.points.salesapp.taxes.TaxCodes;
import com.points.salesapp.taxes.codes.impl.TaxCodeImpl;

public class ShoppingCartTest extends TestCase {

	/**
	 * 	Scenario: Add multiple shoppingItems to shoppingCart to verify tax strategy
	 * 				applied to goods based on country ( CA = DefaultTaxDutiesStrategy )
	 */
	public void testNewShoppingCart() {
		
		BigDecimal amountCheck;
		BigDecimal taxesCheck;
    	ShoppingCart shoppingCart = new ShoppingCartImpl("CA");
    	
    	assert(null!=shoppingCart.getTaxesFactory());
    	assert(null!=shoppingCart.getTaxStrategy());
    	assert(null!=shoppingCart.getItems());
		
		Good newGood = new GoodImpl("bottle of perfume",new BigDecimal(27.99),Good.IMPORTED);
		newGood.setTaxcode(new TaxCodeImpl(TaxCodes.OTHER));
		
		shoppingCart.addShoppingItem(newGood);
		amountCheck = new BigDecimal(32.19).setScale(2,RoundingMode.HALF_EVEN);
		taxesCheck = new BigDecimal(4.20).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shoppingCart.getTotalTaxes().compareTo(taxesCheck));
		assertEquals(0,shoppingCart.getGrandTotal().compareTo(amountCheck));
		
		shoppingCart.addShoppingItem(newGood);
		amountCheck = new BigDecimal(64.38).setScale(2,RoundingMode.HALF_EVEN);
		taxesCheck = new BigDecimal(8.40).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shoppingCart.getTotalTaxes().compareTo(taxesCheck));
		assertEquals(0,shoppingCart.getGrandTotal().compareTo(amountCheck));
	}

	/**
	 * 	Scenario: Change shoppingCart tax strategy by changing country. Verify totals are updated
	 * 				( CA = DefaultTaxDutiesStrategy )
	 * 				( US = AmericanTaxDutiesStrategy )
	 */
	public void testDefferentCountryShoppingCart() {
		
		BigDecimal totalCheck;
		BigDecimal taxesCheck;
    	ShoppingCart shoppingCart = new ShoppingCartImpl("CA");
    	
    	assert(null!=shoppingCart.getTaxesFactory());
    	assert(null!=shoppingCart.getTaxStrategy());
    	assert(null!=shoppingCart.getItems());
		
		Good newGood = new GoodImpl("bottle of perfume",new BigDecimal(27.99),Good.IMPORTED);
		newGood.setTaxcode(new TaxCodeImpl(TaxCodes.OTHER));
		
		shoppingCart.addShoppingItem(newGood);
		totalCheck = new BigDecimal(32.19).setScale(2,RoundingMode.HALF_EVEN);
		taxesCheck = new BigDecimal(4.20).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shoppingCart.getTotalTaxes().compareTo(taxesCheck));
		assertEquals(0,shoppingCart.getGrandTotal().compareTo(totalCheck));
		
		shoppingCart.setCountry("US");
		totalCheck = new BigDecimal(29.99).setScale(2,RoundingMode.HALF_EVEN);
		taxesCheck = new BigDecimal(2.00).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shoppingCart.getTotalTaxes().compareTo(taxesCheck));
		assertEquals(0,shoppingCart.getGrandTotal().compareTo(totalCheck));
	}
}
