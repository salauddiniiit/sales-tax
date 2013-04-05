package com.points.salesapp;
import java.math.BigDecimal;
import java.math.RoundingMode;

import junit.framework.TestCase;

import com.points.salesapp.goods.Good;
import com.points.salesapp.goods.impl.GoodImpl;
import com.points.salesapp.shopping.ShoppingItem;
import com.points.salesapp.shopping.impl.ShoppingItemImpl;
import com.points.salesapp.taxes.TaxCodes;
import com.points.salesapp.taxes.codes.impl.TaxCodeImpl;
import com.points.salesapp.taxes.strategy.TaxDutiesStrategy;
import com.points.salesapp.taxes.strategy.impl.DefaultTaxDutiesStrategyImpl;


public class DefaultTaxStrategyTest extends TestCase {

	/**
	 * 	Scenario: Apply Tax strategy to shoppingItem and make sure values are updated
	 * 				Test rules for Tax code OTHER
	 */
	public void testOtherTaxCodeImpl() {

		Good newBook = new GoodImpl("bottle of perfume",new BigDecimal(18.99),Good.DOMESTIC);
		newBook.setTaxcode(new TaxCodeImpl(TaxCodes.OTHER));
		ShoppingItem shopItem = new ShoppingItemImpl(newBook);

		/* TaxCode drives the values applied by Tax Strategy */
		assert(null!=shopItem.getSaleGood().getTaxcode());
		
		TaxDutiesStrategy taxStrategy = new DefaultTaxDutiesStrategyImpl();
		BigDecimal taxCheck = new BigDecimal(1.90).setScale(2,RoundingMode.HALF_EVEN);
		BigDecimal totalCheck = new BigDecimal(20.89).setScale(2,RoundingMode.HALF_EVEN);
		taxStrategy.setTaxes(shopItem);
		assertEquals(0,shopItem.getSaleTaxes().compareTo(taxCheck));
		assertEquals(0,shopItem.getTotalPrice().compareTo(totalCheck));
		
	}

	/**
	 * 	Scenario: Apply Tax strategy to shoppingItem and make sure values are updated
	 * 				Test rules for Tax code BOOKS ( 0% EXCEMPT )
	 */
	public void testBooksTaxCodeImpl() {

		Good newBook = new GoodImpl("bottle of perfume",new BigDecimal(12.49),Good.DOMESTIC);
		newBook.setTaxcode(new TaxCodeImpl(TaxCodes.BOOKS));
		ShoppingItem shopItem = new ShoppingItemImpl(newBook);

		/* TaxCode drives the values applied by Tax Strategy */
		assert(null!=shopItem.getSaleGood().getTaxcode());
		
		TaxDutiesStrategy taxStrategy = new DefaultTaxDutiesStrategyImpl();
		BigDecimal taxCheck = new BigDecimal(0).setScale(2,RoundingMode.HALF_EVEN);
		BigDecimal totalCheck = new BigDecimal(12.49).setScale(2,RoundingMode.HALF_EVEN);
		taxStrategy.setTaxes(shopItem);
		assertEquals(0,shopItem.getSaleTaxes().compareTo(taxCheck));
		assertEquals(0,shopItem.getTotalPrice().compareTo(totalCheck));
		
	}

	/**
	 * 	Scenario: Apply Tax strategy to shoppingItem and make sure values are updated
	 * 				Test rules for Tax code FOODS ( 0% EXCEMPT )
	 */
	public void testFoodTaxCodeImpl() {

		Good newBook = new GoodImpl("chocolate bar",new BigDecimal(0.85),Good.DOMESTIC);
		newBook.setTaxcode(new TaxCodeImpl(TaxCodes.FOODS));
		ShoppingItem shopItem = new ShoppingItemImpl(newBook);
		
		/* TaxCode drives the values applied by Tax Strategy */
		assert(null!=shopItem.getSaleGood().getTaxcode());
		
		TaxDutiesStrategy taxStrategy = new DefaultTaxDutiesStrategyImpl();
		BigDecimal taxCheck = new BigDecimal(0).setScale(2,RoundingMode.HALF_EVEN);
		BigDecimal totalCheck = new BigDecimal(0.85).setScale(2,RoundingMode.HALF_EVEN);
		taxStrategy.setTaxes(shopItem);
		assertEquals(0,shopItem.getSaleTaxes().compareTo(taxCheck));
		assertEquals(0,shopItem.getTotalPrice().compareTo(totalCheck));
		
	}
	
	/**
	 * 	Scenario: Apply Tax strategy to shoppingItem and make sure values are updated
	 * 				Test rules for Tax code MEDICAL ( 0% EXCEMPT )
	 */
	public void testMedicalTaxCodeImpl() {

		Good newBook = new GoodImpl("packet of headache pills",new BigDecimal(9.75),Good.DOMESTIC);
		newBook.setTaxcode(new TaxCodeImpl(TaxCodes.MEDICAL));
		ShoppingItem shopItem = new ShoppingItemImpl(newBook);

		/* TaxCode drives the values applied by Tax Strategy */
		assert(null!=shopItem.getSaleGood().getTaxcode());
		
		TaxDutiesStrategy taxStrategy = new DefaultTaxDutiesStrategyImpl();
		BigDecimal taxCheck = new BigDecimal(0).setScale(2,RoundingMode.HALF_EVEN);
		BigDecimal totalCheck = new BigDecimal(9.75).setScale(2,RoundingMode.HALF_EVEN);
		taxStrategy.setTaxes(shopItem);
		assertEquals(0,shopItem.getSaleTaxes().compareTo(taxCheck));
		assertEquals(0,shopItem.getTotalPrice().compareTo(totalCheck));
		
	}
	
	/**
	 * 	Scenario: Apply Tax strategy to shoppingItem and make sure values are updated
	 * 				Test rules for Tax code OTHER ( 10% BASIC + 5% IMPORTED )
	 */
	public void testImportedTaxCodeImpl() {

		Good newBook = new GoodImpl("bottle of perfume",new BigDecimal(47.50),Good.IMPORTED);
		newBook.setTaxcode(new TaxCodeImpl(TaxCodes.OTHER));
		ShoppingItem shopItem = new ShoppingItemImpl(newBook);

		/* TaxCode drives the values applied by Tax Strategy */
		assert(null!=shopItem.getSaleGood().getTaxcode());
		
		TaxDutiesStrategy taxStrategy = new DefaultTaxDutiesStrategyImpl();
		BigDecimal taxCheck = new BigDecimal(7.15).setScale(2,RoundingMode.HALF_EVEN);
		BigDecimal totalCheck = new BigDecimal(54.65).setScale(2,RoundingMode.HALF_EVEN);
		taxStrategy.setTaxes(shopItem);
		assertEquals(0,shopItem.getSaleTaxes().compareTo(taxCheck));
		assertEquals(0,shopItem.getTotalPrice().compareTo(totalCheck));
		
	}
	
}
