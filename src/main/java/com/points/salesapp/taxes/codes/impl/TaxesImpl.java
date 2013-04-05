package com.points.salesapp.taxes.codes.impl;

import java.math.BigDecimal;

import com.points.salesapp.taxes.codes.TaxCode;
import com.points.salesapp.taxes.codes.Taxes;

/*
 * this object is used to assign a specific tax value to a tax code
 */

public class TaxesImpl implements Taxes {
	
	private TaxCode 	taxCode;
	private BigDecimal 	value;
	
	public TaxesImpl(String aTaxCode, int aValue) {
		this.taxCode = new TaxCodeImpl(aTaxCode);
		this.value = new BigDecimal(aValue);
	}

	public TaxesImpl(String aTaxCode, BigDecimal aValue) {
		this.taxCode = new TaxCodeImpl(aTaxCode);
		this.value = aValue;
	}

	@Override
	public TaxCode getTaxCode() {
		return taxCode;
	}

	@Override
	public void setTaxCode(TaxCode taxCode) {
		this.taxCode = taxCode;
	}

	@Override
	public BigDecimal getValue() {
		return value;
	}

	@Override
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "TaxCode:"+taxCode.getCode()+" Value:" + value;
	}
}
