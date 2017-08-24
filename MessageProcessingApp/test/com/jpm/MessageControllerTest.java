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
import com.jpm.model.Messages;
import com.jpm.util.Constant;

/**
 * @author nav
 *
 */
public class MessageControllerTest {
	
	
	/**
	 * Test method for {@link com.jpm.controller.MessageController#processMessages()}.
	 * Quantity Test
	 */
	@Test
	public final void processMessagesQtyTest() throws FileNotFoundException {
		MessageController msgCntr = new MessageController();
		assertEquals("13", msgCntr.processMessages().get(2).getQuantity().toString());
	}
	/**
	 * Test method for {@link com.jpm.controller.MessageController#processMessages()}.
	 * Price Test
	 */
	@Test
	public final void processMessagesPriceTest() throws FileNotFoundException {
		MessageController msgCntr = new MessageController();
		assertEquals("3.15", msgCntr.processMessages().get(0).getPrice().toString());
	}
	
	
	//Size Test
	@Test
	public final void processMessageSizeTest() throws FileNotFoundException {
		MessageController msgCntr = new MessageController();
		assertEquals(55, msgCntr.processMessages().size());
	}
	
	/**
	 * Test method for {@link com.jpm.controller.MessageController#getMessageFile()}.
	 * @throws FileNotFoundException
	 *No File Test  
	 */
	@Test(expected=FileNotFoundException.class)
	public void getMessageNoFileTest() throws FileNotFoundException {
		//fail("Not yet implemented");
		MessageController msgCntlr = new MessageController();
		msgCntlr.fileName = "";
		msgCntlr.processMessages();
	}
	
	
	//Empty File Test
	@Test
	public void getMessageEmptyFileTest() throws FileNotFoundException {
		MessageController msgCntr = new MessageController();
		msgCntr.fileName = "/resources/SalesMessagesTest.xml";
		List <Messages> messages = msgCntr.processMessages();
		assertEquals(0, messages.size());
	}
	
	//Wrong File Test
	@Test(expected=FileNotFoundException.class)
	public void getMessageWrongFileTest() throws FileNotFoundException  {
		MessageController msgCntr = new MessageController();
		//putting wrong name of the input xml file
		msgCntr.fileName = "/resources/SalesMessageTest.xml";
		msgCntr.processMessages();
		
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

	/**
	 * Test method for {@link com.jpm.controller.MessageController#processMessages()}.
	 */
	@Test
	public void testProcessMessages() {
		//fail("Not yet implemented");
	}
	
}
