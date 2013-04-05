package com.points.salesapp.goods.impl;

import java.math.BigDecimal;

import com.points.salesapp.goods.Good;
import com.points.salesapp.taxes.codes.TaxCode;
import com.points.salesapp.taxes.codes.impl.TaxCodeImpl;

/*
 *  this object is the basic container that describes the nature 
 *  of the goods like name, base price, source, tax assignment.
 */

public class GoodImpl implements Good {
	
	private String 		name;
	private String 		description;
	private String 		category;
	private BigDecimal	price;
	private TaxCode 	taxcode;
	private Boolean 	imported;

	public GoodImpl() {
		this.name = "";
		this.imported = false;
		this.price = BigDecimal.ZERO;
	}

	public GoodImpl(String name, BigDecimal price, Boolean isImported) {
		this.name = name;
		this.imported = isImported;
		this.price = price;
	}

	public GoodImpl(String name, BigDecimal price, Boolean isImported, String taxcode) {
		this.name = name;
		this.imported = isImported;
		this.price = price;
		this.taxcode = new TaxCodeImpl(taxcode);
	}
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getCategory() {
		return category;
	}

	@Override
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public TaxCode getTaxcode() {
		return taxcode;
	}

	@Override
	public void setTaxcode(TaxCode taxcode) {
		this.taxcode = taxcode;
	}

	@Override
	public Boolean isImported() {
		return imported;
	}

	@Override
	public void setImported(Boolean imported) {
		this.imported = imported;
	}

	@Override
	public void markAsImported() {
		this.imported = true;
	}

	@Override
	public String getUniqueId() {
		return name + taxcode.getCode() + imported;
	}

	@Override
	public String toString() {
		return (  "GoodsName:" 		+ name  
				+ "  TaxCode:" 		+ taxcode.toString()  
				+ "  BasePrice:" 	+ price.toString()
				+ "  Imported:" 	+ imported  
				);
	}
	
}
