package com.points.salesapp;

import java.math.BigDecimal;

import com.points.salesapp.goods.Good;
import com.points.salesapp.goods.impl.GoodImpl;
import com.points.salesapp.shopping.ShoppingCart;
import com.points.salesapp.shopping.impl.ShoppingCartImpl;
import com.points.salesapp.taxes.TaxCodes;

/**
 * Points.com Interview Assignment
 *
 */
public class TaxApp 
{

	/*
	 * The main method contains an implementation that provides the output described on the problem details 
	 */
	
    public static void main( String[] args )
    {
    	ShoppingCart shoppingCart = new ShoppingCartImpl("CA");
    	
    	shoppingCart.addShoppingItem(new GoodImpl("Book",new BigDecimal(12.49),Good.DOMESTIC,TaxCodes.BOOKS));
    	shoppingCart.addShoppingItem(new GoodImpl("music CD",new BigDecimal(14.99),Good.DOMESTIC,TaxCodes.MUSIC));
    	shoppingCart.addShoppingItem(new GoodImpl("chocolate bar",new BigDecimal(0.85),Good.DOMESTIC,TaxCodes.FOODS));
    	shoppingCart.printReceipt();
    	
    	shoppingCart.clearShoppingCart();
    	shoppingCart.addShoppingItem(new GoodImpl("box of chocolates",new BigDecimal(10.00),Good.IMPORTED,TaxCodes.FOODS));
    	shoppingCart.addShoppingItem(new GoodImpl("bottle of perfume",new BigDecimal(47.50),Good.IMPORTED,TaxCodes.OTHER));
    	shoppingCart.printReceipt();
    	
    	shoppingCart.clearShoppingCart();
    	shoppingCart.addShoppingItem(new GoodImpl("bottle of perfume",new BigDecimal(27.99),Good.IMPORTED,TaxCodes.OTHER));
    	shoppingCart.addShoppingItem(new GoodImpl("bottle of perfume",new BigDecimal(18.99),Good.DOMESTIC,TaxCodes.OTHER));
    	shoppingCart.addShoppingItem(new GoodImpl("packet of headache pills",new BigDecimal(9.75),Good.DOMESTIC,TaxCodes.MEDICAL));
    	shoppingCart.addShoppingItem(new GoodImpl("box of chocolates",new BigDecimal(11.25),Good.IMPORTED,TaxCodes.FOODS));
    	shoppingCart.printReceipt();
    }
}
