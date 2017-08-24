package com.jpm.model;

import java.math.BigDecimal;
import com.jpm.model.*;
public class Messages {
	
	private String productType;
	
	private BigDecimal price;
	
	private BigDecimal quantity=BigDecimal.ONE;
	
	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}


	private Operation operation=Operation.SALE;
	
	public String getProductType() {
		return productType;
	}
	
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	
	public BigDecimal getPrice() {
		return price;
	}
	
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	
	public Operation getOperation() {
		return operation;
	}
	
	
	public void setOperation(Operation command) {
		this.operation = command;
	}	
	
}