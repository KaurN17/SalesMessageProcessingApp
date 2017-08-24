package com.jpm.controller;

/**
 * @author nav
 *
 */

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jpm.dao.AdjustDataAccessObj;
import com.jpm.dao.SalesDataAccessObj;
import com.jpm.model.*;

/**
 * Class:For populating sales according 
 * to their operation type
 */
public class SalesController {
	
	private static final Logger logger = Logger.getLogger(SalesController.class);
	private SalesDataAccessObj salesDao;
	private AdjustDataAccessObj adjustmentDao;
	
	public SalesController(SalesDataAccessObj salesDao ,AdjustDataAccessObj adjustmentDao) {
		this.salesDao = salesDao;
		this.adjustmentDao = adjustmentDao;
	}
	
	/**
	 * Method:Computes every sale according to its operation type
	 *
	 * @param message 
	 */
	public void computeSales (Messages message) {
		try{
			List<Sales> sales= salesDao.getAllSales();
			if (!(message.getOperation().equals(Operation.SALE))) {
				logger.info("Computing for adjustment sales price : data which has operation " );
				List<Adjustment> adjustments= adjustmentDao.getAllAdjustments();
				modifyAdjustments(sales,adjustments,message);
					
			} else {
				logger.info("Computing for normal sales price:data which has no operation  type" );
				addSales(sales, message);
			}
		}catch( Exception ex ) {
			System.out.println(ex.toString());
			logger.log( Level.ERROR, ex.toString(), ex);
		}
	}
	
	/**
	 * Method:Checks for the operation type and performs the operation on the sale
	 * @param sales the sales
	 * @param adjustments the adjustments
	 * @param message the message
	 */
	public void modifyAdjustments (List<Sales> sales, List<Adjustment> adjustments, Messages message) {
		try{
			logger.debug("Inside modifyAdjustments");
			BigDecimal 	totalBefore = BigDecimal.ZERO,
						totalAfter = BigDecimal.ZERO,
						totalQty = BigDecimal.ZERO,
						messagePrice = BigDecimal.ZERO,
						salePrice = BigDecimal.ZERO,
						saleQty = BigDecimal.ZERO;
			for (Sales sale:sales) {
				messagePrice = message.getPrice();
				if (sale.getProductType().equals(message.getProductType())) {
					salePrice=sale.getPrice();
					saleQty=sale.getQty();
					//get the total price before any operation
					totalBefore = totalBefore.add(salePrice.multiply(saleQty));
					switch (message.getOperation()) {
						case ADD: 
							sale.setPrice(salePrice.add(messagePrice));
							break;
						case SUBTRACT: 
							sale.setPrice(salePrice.subtract(messagePrice));
							break;
						case MULTIPLY: 
							sale.setPrice(salePrice.multiply(messagePrice));
							break;
						default:
							break;
					}
					//get total price and total quantity after the operation 
					totalAfter = totalAfter.add(sale.getPrice().multiply(saleQty));
					totalQty = totalQty.add(saleQty);
				}
			}
			adjustments.add(new Adjustment(
											totalBefore,
											totalAfter,
											totalQty,
											message.getOperation(),
											message.getPrice(),
											message.getProductType())
										  );
		}catch( Exception ex ) {
			System.out.println(ex.toString());
			logger.log( Level.ERROR, ex.toString(), ex);
		}
	}
	
	/**
	 *Method: Adds the normal sales.
	 *
	 * @param sales
	 * @param message 
	 */
	public void addSales(List<Sales> sales, Messages message){
		try{
			sales.add(new Sales(
								message.getProductType(),
								message.getPrice(),
								message.getQuantity())
							  );
		}catch( Exception ex ) {
			System.out.println(ex.toString());
			logger.log( Level.ERROR, ex.toString(), ex);
		}
	}
}
