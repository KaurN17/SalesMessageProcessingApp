package com.jpm.dao;

import java.util.ArrayList;
import java.util.List;

import com.jpm.model.Adjustment;

public class AdjustDataAccessObj {
	
	private List<Adjustment> allAdjustments = new ArrayList();
	
	/**
	 * Method:Get all adjustments.
	 *
	 * @return the adjustments
	 */
	public List<Adjustment> getAllAdjustments() {
		return this.allAdjustments;
	}
}
