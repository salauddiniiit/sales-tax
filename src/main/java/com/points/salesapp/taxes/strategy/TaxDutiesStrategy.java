package com.points.salesapp.taxes.strategy;

import java.util.Collection;

import com.points.salesapp.shopping.ShoppingItem;

/*
 * this object has a group of taxes that can be applied to goods and the business logic to apply them
 */

public interface TaxDutiesStrategy {

	public void setTaxes(ShoppingItem item);
	public void setTaxes(Collection<ShoppingItem> items);
}
