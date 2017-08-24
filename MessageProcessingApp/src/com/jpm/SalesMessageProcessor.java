package com.jpm;
import com.jpm.controller.MessageController;
import com.jpm.controller.SalesController;
import com.jpm.dao.AdjustDataAccessObj;
import com.jpm.dao.SalesDataAccessObj;
import com.jpm.model.Messages;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author nav
 *
 *Class:Entry class with main method
 *Instantiates the dao classes 
 *Calls method for generating report
 */
public class SalesMessageProcessor {

	
		private static final Logger logger = Logger.getLogger(SalesMessageProcessor.class);
		
		/**
		 * The main method.
		 *
		 * @param args the arguments
		 */
		public static void main (String[] args) {
			try{
				logger.info("Inside main method");
				SalesDataAccessObj salesDao = new SalesDataAccessObj();
				AdjustDataAccessObj adjustementsDao = new AdjustDataAccessObj();
				List <Messages> allMessages = new MessageController().processMessages();
				SalesController salesCtrl = new SalesController(salesDao,adjustementsDao);
				int count = 0;
				for (Messages message: allMessages) {
					count++;
					//Updating sales according to operations
					salesCtrl.computeSales(message);
					// check for count : display simple report after 10 sales
					if (count % 10 == 0){
						logger.info("Report display after 10 sales" );
						ReportDisplay.reportAfter10(salesDao.getAllSales(),count);
					}
					// check for count : display adjustment report after 50 sales
					if (count % 50 == 0){
						logger.debug("Report display after 50 sales" );
						ReportDisplay.adjustmentReport(adjustementsDao.getAllAdjustments());
					}
				}
			}catch( Exception ex ) {
				System.out.println(ex.toString());
				logger.log( Level.ERROR, ex.toString(), ex);
			}
		}
	}