package com.jpm.model;
import java.math.BigDecimal;
import com.jpm.model.Operation;


/**
 *Class: Adjustment.
 */
public class Adjustment {

	private BigDecimal totalBefore;
	
	private BigDecimal totalAfter;
	
	private BigDecimal totalQty;
	
	private Operation operation;
	
	private BigDecimal delta;
	
	private String productType;

	public Adjustment(BigDecimal totalBefore, BigDecimal totalAfter, BigDecimal totalQty, Operation operation,
			BigDecimal delta, String productType) {
		this.totalBefore = totalBefore;
		this.totalAfter = totalAfter;
		this.totalQty = totalQty;
		this.operation = operation;
		this.delta = delta;
		this.productType = productType;
	}
	
	public String getProductType() {
		return productType;
	}
	
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	public BigDecimal getTotalBefore() {
		return totalBefore;
	}
	
	public void setTotalBefore(BigDecimal totalBefore) {
		this.totalBefore = totalBefore;
	}
	
	public BigDecimal getTotalAfter() {
		return totalAfter;
	}
	public void setTotalAfter(BigDecimal totalAfter) {
		this.totalAfter = totalAfter;
	}
	
	public BigDecimal getTotalQty() {
		return totalQty;
	}
	
	public void setTotalQty(BigDecimal totalQty) {
		this.totalQty = totalQty;
	}
	
	public Operation getOperation() {
		return operation;
	}
	
	public void setOperation(Operation command) {
		this.operation = command;
	}
	
	public BigDecimal getDelta() {
		return delta;
	}
	
	public void setDelta(BigDecimal delta) {
		this.delta = delta;
	}
	
	public String toString () {
		return  "|"+
				String.format("%1$16s",getProductType()).toUpperCase() + 
				"|"+
				String.format("%1$16s", getOperation())+
				"|"+
				String.format("%1$12s",getDelta())+
				"|"+
				String.format("%1$10s",getTotalQty())+
				"|"+
				String.format("%1$18s",getTotalBefore())+
				"|"+
				String.format("%1$16s",getTotalAfter())+
				"|";

	}
	
}
