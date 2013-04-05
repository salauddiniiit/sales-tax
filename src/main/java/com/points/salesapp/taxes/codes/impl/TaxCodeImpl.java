package com.points.salesapp.taxes.codes.impl;

import com.points.salesapp.taxes.codes.TaxCode;

/*
 * this object describes a unique tax identifier that is use to correlate goods with tax brackets
 */

public class TaxCodeImpl implements TaxCode {

	private String code;
	
	public TaxCodeImpl(String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public boolean equals(TaxCode taxCode) {
		if (null == taxCode) {
			return false;
		}
		return code.equals(taxCode.getCode());
	}

	@Override
	public String toString() {
		return "TaxCode: " + code;
	}
	
}
