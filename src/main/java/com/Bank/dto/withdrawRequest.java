package com.Bank.dto;

import java.math.BigDecimal;

public class withdrawRequest {
	
	 private BigDecimal amount;

	    public withdrawRequest() {
	    }

	    public BigDecimal getAmount() {
	        return amount;
	    }

	    public void setAmount(BigDecimal amount) {
	        this.amount = amount;
	    }
}
