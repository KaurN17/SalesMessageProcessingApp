package com.jpm.dao;

import java.util.ArrayList;
import java.util.List;

import com.jpm.model.Sales;

public class SalesDataAccessObj {
	
	private List<Sales> allSales = new ArrayList();
	
	/**
	 * Method: Gets all sales.
	 *
	 * @return the sales
	 */
	public List<Sales> getAllSales() {
		return this.allSales;
	}
}
