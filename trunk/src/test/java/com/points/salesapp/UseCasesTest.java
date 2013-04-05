package com.points.salesapp;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.points.salesapp.goods.Good;
import com.points.salesapp.goods.impl.GoodImpl;
import com.points.salesapp.shopping.ShoppingCart;
import com.points.salesapp.shopping.impl.ShoppingCartImpl;
import com.points.salesapp.taxes.TaxCodes;
import com.points.salesapp.taxes.codes.impl.TaxCodeImpl;

import junit.framework.TestCase;

public class UseCasesTest extends TestCase {

	/**
	 * 	Scenario: Use case 1 using DefaultTaxDutiesStrategy 
	 */
	public void testUseCase1Cart() {
		
		BigDecimal amountCheck;
		BigDecimal taxesCheck;

		//Setup Shopping Cart using Domestic Tax Strategy
		ShoppingCart shoppingCart = new ShoppingCartImpl("CA");
		
    	//Add book good with price 12.49. Final price is 12.49
    	Good newGood1 = new GoodImpl("book",new BigDecimal(12.49),Good.DOMESTIC);
		newGood1.setTaxcode(new TaxCodeImpl(TaxCodes.BOOKS));
		shoppingCart.addShoppingItem(newGood1);
		amountCheck = new BigDecimal(12.49).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shoppingCart.getShoppingItem(newGood1).getTotalPrice().compareTo(amountCheck));
		
    	//Add music good with price 14.99. Final price is 16.49
		Good newGood2 = new GoodImpl("music CD",new BigDecimal(14.99),Good.DOMESTIC);
		newGood2.setTaxcode(new TaxCodeImpl(TaxCodes.MUSIC));
		shoppingCart.addShoppingItem(newGood2);
		amountCheck = new BigDecimal(16.49).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shoppingCart.getShoppingItem(newGood2).getTotalPrice().compareTo(amountCheck));

    	//Add food good with price 0.85. Final price is 0.85
		Good newGood3 = new GoodImpl("chocolate bar",new BigDecimal(0.85),Good.DOMESTIC);
		newGood3.setTaxcode(new TaxCodeImpl(TaxCodes.FOODS));
		shoppingCart.addShoppingItem(newGood3);
		amountCheck = new BigDecimal(0.85).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shoppingCart.getShoppingItem(newGood3).getTotalPrice().compareTo(amountCheck));
		
    	//Receipt total is 29.83 (book+music+good+taxes). Taxes is 1.50
		amountCheck = new BigDecimal(29.83).setScale(2,RoundingMode.HALF_EVEN);
		taxesCheck = new BigDecimal(1.50).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shoppingCart.getTotalTaxes().compareTo(taxesCheck));
		assertEquals(0,shoppingCart.getGrandTotal().compareTo(amountCheck));
		
		shoppingCart.printReceipt();
	}

	/**
	 * 	Scenario: Use case 2 using DefaultTaxDutiesStrategy 
	 */
	public void testUseCase2Cart() {
		
		BigDecimal amountCheck;
		BigDecimal taxesCheck;

		//Setup Shopping Cart using Domestic Tax Strategy
		ShoppingCart shoppingCart = new ShoppingCartImpl("CA");
		
    	//Add imported food good with price 10.00. Final price is 10.50
    	Good newGood1 = new GoodImpl("box of chocolates",new BigDecimal(10.00),Good.IMPORTED);
		newGood1.setTaxcode(new TaxCodeImpl(TaxCodes.FOODS));
		shoppingCart.addShoppingItem(newGood1);
		amountCheck = new BigDecimal(10.50).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shoppingCart.getShoppingItem(newGood1).getTotalPrice().compareTo(amountCheck));
		
    	//Add imported other good with price 47.50. Final price is 54.65
		Good newGood2 = new GoodImpl("bottle of perfume",new BigDecimal(47.50),Good.IMPORTED);
		newGood2.setTaxcode(new TaxCodeImpl(TaxCodes.OTHER));
		shoppingCart.addShoppingItem(newGood2);
		amountCheck = new BigDecimal(54.65).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shoppingCart.getShoppingItem(newGood2).getTotalPrice().compareTo(amountCheck));

    	//Receipt total is 65.15 (book+music+good+taxes). Taxes is 7.65
		amountCheck = new BigDecimal(65.15).setScale(2,RoundingMode.HALF_EVEN);
		taxesCheck = new BigDecimal(7.65).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shoppingCart.getTotalTaxes().compareTo(taxesCheck));
		assertEquals(0,shoppingCart.getGrandTotal().compareTo(amountCheck));
		
		shoppingCart.printReceipt();
	}

	/**
	 * 	Scenario: Use case 3 using DefaultTaxDutiesStrategy 
	 */
	public void testUseCase3Cart() {
		
		BigDecimal amountCheck;
		BigDecimal taxesCheck;

		//Setup Shopping Cart using Domestic Tax Strategy
		ShoppingCart shoppingCart = new ShoppingCartImpl("CA");
		
    	//Add imported other good with price 27.99. Final price is 32.19
		Good newGood1 = new GoodImpl("bottle of perfume",new BigDecimal(27.99),Good.IMPORTED);
		newGood1.setTaxcode(new TaxCodeImpl(TaxCodes.OTHER));
		shoppingCart.addShoppingItem(newGood1);
		amountCheck = new BigDecimal(32.19).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shoppingCart.getShoppingItem(newGood1).getTotalPrice().compareTo(amountCheck));

    	//Add domestic other good with price 18.99. Final price is 20.89
		Good newGood2 = new GoodImpl("bottle of perfume",new BigDecimal(18.99),Good.DOMESTIC);
		newGood2.setTaxcode(new TaxCodeImpl(TaxCodes.OTHER));
		shoppingCart.addShoppingItem(newGood2);
		amountCheck = new BigDecimal(20.89).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shoppingCart.getShoppingItem(newGood2).getTotalPrice().compareTo(amountCheck));

    	//Add medical food good with price 9.75. Final price is 9.75
    	Good newGood3 = new GoodImpl("pack of headache pills",new BigDecimal(9.75),Good.DOMESTIC);
    	newGood3.setTaxcode(new TaxCodeImpl(TaxCodes.MEDICAL));
		shoppingCart.addShoppingItem(newGood3);
		amountCheck = new BigDecimal(9.75).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shoppingCart.getShoppingItem(newGood3).getTotalPrice().compareTo(amountCheck));
		
    	//Add imported food good with price 11.25. Final price is 11.85
    	Good newGood4 = new GoodImpl("box of chocolates",new BigDecimal(11.25),Good.IMPORTED);
    	newGood4.setTaxcode(new TaxCodeImpl(TaxCodes.FOODS));
		shoppingCart.addShoppingItem(newGood4);
		amountCheck = new BigDecimal(11.85).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shoppingCart.getShoppingItem(newGood4).getTotalPrice().compareTo(amountCheck));
		
    	//Receipt total is 74.68 (book+music+good+taxes). Taxes is 6.70
		amountCheck = new BigDecimal(74.68).setScale(2,RoundingMode.HALF_EVEN);
		taxesCheck = new BigDecimal(6.70).setScale(2,RoundingMode.HALF_EVEN);
		assertEquals(0,shoppingCart.getTotalTaxes().compareTo(taxesCheck));
		assertEquals(0,shoppingCart.getGrandTotal().compareTo(amountCheck));
		
		shoppingCart.printReceipt();
	}

}
