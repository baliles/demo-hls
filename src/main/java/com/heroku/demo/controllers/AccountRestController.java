package com.heroku.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heroku.demo.entities.Account;
import com.heroku.demo.model.ResponseMessage;
import com.heroku.demo.services.AccountService;

/*
 * REST Services
 */
@Controller
@RequestMapping(value="/api/v1/account")
public class AccountRestController {
	
	private static Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;
		
	/*
	 * READ METHODS
	 */

	
	@RequestMapping(value="/page", method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage getPage(@RequestParam int page, @RequestParam int records) {

		if (logger.isDebugEnabled())
			logger.debug("AccountService -> getPage");
		
		ResponseMessage responseMessage = new ResponseMessage();
		try {
			responseMessage.setData(accountService.findAll(page, records));
		} catch (Exception e) {
			logger.error("AccountController -> getPage", e);
			responseMessage.setError(-1, "Unable to get page for Account: " + e.getMessage());
		}
		return responseMessage;
		
	}


//	@RequestMapping(value="/count", method=RequestMethod.GET,
//			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseMessage getCount() {
//
//		if (logger.isDebugEnabled())
//			logger.debug("AccountService -> getAll");
//		
//		ResponseMessage responseMessage = new ResponseMessage();
//		try {
//			responseMessage.setData(accountService.count());
//		} catch (Exception e) {
//			logger.error("AccountController -> getAll", e);
//			responseMessage.setError(-1, "Unable to get all Account: " + e.getMessage());
//		}
//		return responseMessage;
//		
//	}

//	@RequestMapping(value="/page", method=RequestMethod.GET,
//			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseMessage getPage(@RequestParam int page,@RequestParam int size) {
//
//		if (logger.isDebugEnabled())
//			logger.debug("AccountService -> getPage(" + page + "," + size + ")");
//		
//		ResponseMessage responseMessage = new ResponseMessage();
//		try {
//			responseMessage.setData(accountService.getPage(page, size));
//		} catch (Exception e) {
//			logger.error("AccountController -> getAll", e);
//			responseMessage.setError(-1, "Unable to get all Account: " + e.getMessage());
//		}
//		return responseMessage;
//		
//	}
	
//	@RequestMapping(value="/elements", method=RequestMethod.GET,
//			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseMessage getDropDownElements() {
//
//		if (logger.isDebugEnabled())
//			logger.debug("AccountService -> getDropDownElements");
//		
//		ResponseMessage responseMessage = new ResponseMessage();
//		try {
//			responseMessage.setData(accountService.getDropDownValues());
//		} catch (Exception e) {
//			logger.error("AccountController -> getDropDownElements", e);
//			responseMessage.setError(-1, "Unable to getDropDownElements for Account: " + e.getMessage());
//		}
//		return responseMessage;
//		
//	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public ResponseMessage getOne(@PathVariable Integer id) {
		if (logger.isDebugEnabled())
			logger.debug("AccountService -> getOne(" + id + ")");
		ResponseMessage responseMessage = new ResponseMessage();
		
		try {
			responseMessage.setData(accountService.get(id));
		} catch (Exception e) {
			logger.error("AccountController -> create", e);
			responseMessage.setError(-1,
					"Unable to create Account: " + id + ",Error:"  + e.getMessage());
		}
		
		return responseMessage;
	}

/*	@RequestMapping(value="{account}/device/{device}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public ResponseMessage getByDeviceId(@PathVariable String account, @PathVariable String device) {
		if (logger.isDebugEnabled())
			logger.debug("AccountService -> findByDevice(" + device + ")");
		ResponseMessage responseMessage = new ResponseMessage();
		
		try {
			responseMessage.setData(accountService.findByDevice(device));
		} catch (Exception e) {
			logger.error("AccountController -> create", e);
			responseMessage.setError(-1,
					"Unable to get accounts for device: " + device + ",Error:"  + e.getMessage());
		}
		
		return responseMessage;
	}
*/

	
	/*
	 * DML Methods
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage create(@RequestBody Account element) {

		if (logger.isDebugEnabled())
			logger.debug("AccountService -> create:" + element);

		ResponseMessage responseMessage = new ResponseMessage();
		
		try {
			responseMessage.setData(accountService.create(element));
		} catch (Exception e) {
			logger.error("AccountController -> create", e);
			responseMessage.setError(-1,
					"Unable to create Account: " + element + ",Error:"  + e.getMessage());
		}
		
		return responseMessage;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage edit(@PathVariable Integer id, @RequestBody Account element) {
		if (logger.isDebugEnabled())
			logger.debug("AccountService -> edit(" + id + "):" + element);

		ResponseMessage responseMessage = new ResponseMessage();
		
		try {
			element.setId(id);
			responseMessage.setData(accountService.create(element));
		} catch (Exception e) {
			logger.error("AccountController -> edit", e);
			responseMessage.setError(-1,
					"Unable to edit Account: " + element + ",Error:" + e.getMessage());
		}
		
		return responseMessage;
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage delete(@PathVariable Integer id) {

		if (logger.isDebugEnabled())
			logger.debug("AccountService -> delete(" + id + ")");

		ResponseMessage responseMessage = new ResponseMessage();
		try {
			accountService.delete(id);
		} catch (Exception e) {
			logger.error("AccountController -> delete", e);
			responseMessage.setError(-1, "Unable to delete Account: " + id + ",Error:"  + e.getMessage());
		}
		return responseMessage;
	}
	
}
