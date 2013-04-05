package com.points.salesapp.taxes.codes;

import java.math.BigDecimal;

/*
 * this object is used to assign a specific tax value to a tax code
 */

public interface Taxes {

	public TaxCode getTaxCode();

	public void setTaxCode(TaxCode taxCode);

	public BigDecimal getValue();

	public void setValue(BigDecimal value);


}
