package com.points.salesapp.goods;

import java.math.BigDecimal;

import com.points.salesapp.taxes.codes.TaxCode;

/*
 *  this object is the basic container that describes the nature 
 *  of the goods like name, base price, source, tax assignment.
 */

public interface Good {

	public static boolean IMPORTED = true;
	public static boolean DOMESTIC = false;
	
	public String getName();
	public void setName(String name);
	public String getDescription();
	public void setDescription(String description);
	public String getCategory();
	public void setCategory(String category);
	public BigDecimal getPrice();
	public void setPrice(BigDecimal price);
	public TaxCode getTaxcode();
	public void setTaxcode(TaxCode taxcode);
	public Boolean isImported();
	public void setImported(Boolean imported);
	public void markAsImported();
	public String getUniqueId();
}
