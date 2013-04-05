package com.points.salesapp.taxes.codes;

/*
 * this object describes a unique tax identifier that is use to correlate goods with tax brackets
 */

public interface TaxCode {
	
	public String getCode();
	public void setCode(String code);
	public boolean equals(TaxCode aTaxCode);
	
}
