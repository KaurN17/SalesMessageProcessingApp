package com.jpm;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jpm.model.Adjustment;
import com.jpm.model.Sales;
/**
 * Class:For displaying the report
 */
public class ReportDisplay {
	
	private static final Logger logger = Logger.getLogger(ReportDisplay.class);

	/**
	 * Method: Generates report after each 10 sales.
	 *
	 * @param sales the sales
	 */
	public static void reportAfter10 (List<Sales> sales, int count) {
		try{
			Map<String,Sales> resultHM = new HashMap();
			BigDecimal totalValue = BigDecimal.ZERO;
			BigDecimal totalQty = BigDecimal.ZERO;
			for (Sales sale : sales) {
				Sales totalSale = resultHM.get(sale.getProductType());
				BigDecimal value = sale.getQty().multiply(sale.getPrice());
				totalValue = totalValue.add(value);
				totalQty = totalQty.add(sale.getQty());
				if (totalSale!=null) {
					totalSale.setQty(totalSale.getQty().add(sale.getQty()));
					totalSale.setPrice(totalSale.getPrice().add(value));
				} else {
					totalSale= new Sales(sale.getProductType(), value, sale.getQty());
				}
				resultHM.put(sale.getProductType(), totalSale);
			}
			
			System.out.println(" Report after "+ count + " sales: "+ "\n");
			System.out.println(" ******************************************* ");
			System.out.println("|       Product Type|       Price|  Quantity|");
			System.out.println("|*******************|************|**********|");
			for (Sales sale: resultHM.values()) {
				System.out.println(sale);
			}
			System.out.println("|-------------------|------------|----------|");
			System.out.println("|              Total|"+String.format("%1$12s", totalValue.toString())+"|" +String.format("%1$10s", totalQty.toString())+"|");
			System.out.println(" ------------------------------------------- ");
			System.out.println();
		}catch( Exception ex ) {
			System.out.println(ex.toString());
		    logger.log( Level.ERROR, ex.toString(), ex);
		}
	}
	
	/**
	 * Method: generates adjustment report after 50 sales.
	 *
	 * @param adjustements the adjustments
	 */
	public static void adjustmentReport (List<Adjustment> adjustments) {
		try{
			logger.debug("Inside the adjustmentReport method" );
			System.out.println(" Pausing !!!! 50 Messages processed!\n");
			System.out.println(" Adjustment Report:\n");
			System.out.println(" ********************************************************************************************* ");
			System.out.println("|    Product Type|       Operation|       Price|  Quantity|   Previous Amount|      New Amount|");
			System.out.println("|****************|****************|************|**********|******************|****************|");
			for (Adjustment adjustment: adjustments) {
				System.out.println(adjustment);
			}
			System.out.println(" --------------------------------------------------------------------------------------------- ");
			logger.debug("Exiting the adjustmentReport method" );
			System.exit(1);
		}catch( Exception ex ) {
			System.out.println(ex.toString());
			logger.log( Level.ERROR, ex.toString(), ex);
		}
	}
}
