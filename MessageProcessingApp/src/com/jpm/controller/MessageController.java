/**
 * 
 */
package com.jpm.controller;

/**
 * @author nav
 *
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.jpm.model.*;
import com.jpm.util.*;

/**
 * Class:Uses SAX parser to parse sales 
 * messages stored in XML file taken as input
 */
public class MessageController {
	
	private static final Logger logger = Logger.getLogger(MessageController.class);
	
	public String fileName = null;
	
	/**
	 * Method:Processing each message from XML.
	 *
	 * @return the list of messages
	 * @throws FileNotFoundException the file not found exception
	 */
	public List<Messages> processMessages() throws FileNotFoundException{
		
		// messagesList : Need to store messages from the XML parsing
		final List<Messages> messagesList = new ArrayList<Messages>();
		
		// SAX event handlers
		DefaultHandler handler = new DefaultHandler() {
			// Quantity Flag
			boolean quantityFlag = false;
			// Operation Flag
			boolean operationFlag = false;
			Messages message;
			
			public void startElement(String uri, String localName,String qName,
		                Attributes attributes) throws SAXException {
				if (qName.equalsIgnoreCase(Constant.MESSAGE)) {
					logger.debug("Inside startElement:Reading new message." );
					message = new Messages();
					message.setProductType(attributes.getValue(Constant.PRODUCTTYPE));
					if(attributes.getValue(Constant.PRICE)!=null)
					message.setPrice(new BigDecimal(attributes.getValue(Constant.PRICE)));
				}
				if (qName.equalsIgnoreCase(Constant.QUANTITY)) {
					quantityFlag = true;
				}
				if (qName.equalsIgnoreCase(Constant.OPERATION)) {
					operationFlag = true;
				}
			}
			
			public void endElement(String uri, String localName,
				String qName) throws SAXException {
				logger.debug("adding message to list." );
				if (qName.equalsIgnoreCase(Constant.MESSAGE))
					messagesList.add(message);
			}
			
			public void characters(char ch[], int start, int length) throws SAXException {
				logger.debug("setting quantity");
				if (quantityFlag) {
					message.setQuantity(new BigDecimal(new String(ch, start, length)));
					quantityFlag = false;
				}

				logger.debug("setting operation");
				if (operationFlag) {
					message.setOperation(Operation.valueOf(new String(ch, start, length).toUpperCase()));
					operationFlag = false;
				}
			}
		};
	
		SAXParserFactory saxFactory = SAXParserFactory.newInstance();
		try {
			logger.info("Into the SAXFactory method : Creating a SAXParser" );
			SAXParser saxParser = saxFactory.newSAXParser();
			// get input stream
			InputStream in = getMessageFile(); 
			logger.info("The file is " + in );
			if(in!=null){
			saxParser.parse(in,handler);
			}
			else{
				throw new FileNotFoundException();
			}
		} 
		catch (SAXException e) {
			e.printStackTrace();
			logger.log( Level.ERROR, e.toString(), e);
		}
		 catch (FileNotFoundException e) {
			 logger.log( Level.ERROR, e.toString(), e);
				throw e;
			}
		catch (IOException e) {
			e.printStackTrace();
			logger.log( Level.ERROR, e.toString(), e);
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.log( Level.ERROR, e.toString(), e);
		}
	return messagesList;
	}
	
	/**
	 * Method:Gets the messages xml file.
	 *
	 * @return the input data
	 * @throws FileNotFoundException the file not found exception
	 */
	public InputStream getMessageFile() throws FileNotFoundException{
		logger.info("Inside getMessageFile method: Getting the filename");
		String file = "";
		if(fileName == null)
			file = Constant.MESSAGESFILE;
		else{
			file = fileName;
		}
		if(file.equals(""))
			throw new FileNotFoundException();
		logger.info("fileName is "+ file);
		return getClass().getResourceAsStream(file);
	}
}
