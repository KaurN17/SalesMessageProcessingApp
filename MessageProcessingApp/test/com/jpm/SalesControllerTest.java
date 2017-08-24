/**
 * 
 */
package com.jpm;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jpm.controller.MessageController;
import com.jpm.controller.SalesController;
import com.jpm.dao.AdjustDataAccessObj;
import com.jpm.dao.SalesDataAccessObj;
import com.jpm.model.Messages;

/**
 * @author nav
 *
 */
public class SalesControllerTest {

	
	/**
	 * Test 
	 * @throws FileNotFoundException
	 * Test for normal sales
	 * And
	 * Sales with operation 
	 */
	@Test
	public void computeSalesSizeTest() throws FileNotFoundException {
		SalesDataAccessObj salesDao = new SalesDataAccessObj();
		AdjustDataAccessObj adjustmentDao = new AdjustDataAccessObj();
		MessageController msgCntr = new MessageController();
		List <Messages> allMessages = msgCntr.processMessages();
		SalesController sc = new SalesController(salesDao,adjustmentDao);
		for (Messages message: allMessages) {
			sc.computeSales(message);
		}
		assertEquals(31, salesDao.getAllSales().size());
		assertEquals(24, adjustmentDao.getAllAdjustments().size());
		
	}
	
	
	/**
	 * Test 
	 * @throws FileNotFoundException the file not found exception
	 * Test for adjustment of first two sales in the XML
	 */
	@Test
	public void computeSalesTest() throws FileNotFoundException {
		MessageController msgCntr = new MessageController();
		SalesDataAccessObj salesDao = new SalesDataAccessObj();
		AdjustDataAccessObj adjDao = new AdjustDataAccessObj();
		SalesController sc = new SalesController(salesDao, adjDao);
		List <Messages> messagesList = msgCntr.processMessages();
		for (int i=0; i<=1; i++) {
			if(messagesList.get(i)!=null){
			sc.computeSales(messagesList.get(i));
			}
			
		}
		assertEquals("3.15", adjDao.getAllAdjustments().get(0).getTotalBefore().toString());
		assertEquals("6.40", adjDao.getAllAdjustments().get(0).getTotalAfter().toString());

		
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

}





