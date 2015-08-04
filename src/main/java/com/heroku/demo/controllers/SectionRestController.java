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

import com.heroku.demo.entities.Section;
import com.heroku.demo.model.ResponseMessage;
import com.heroku.demo.services.SectionService;

/*
 * REST Services
 */
@Controller
@RequestMapping(value="/api/v1/section")
public class SectionRestController {
	
	private static Logger logger = LoggerFactory.getLogger(SectionController.class);
	
	@Autowired
	private SectionService sectionService;
		
	/*
	 * READ METHODS
	 */

	
	@RequestMapping(value="/page", method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage getPage(@RequestParam int page, @RequestParam int records) {

		if (logger.isDebugEnabled())
			logger.debug("SectionService -> getPage");
		
		ResponseMessage responseMessage = new ResponseMessage();
		try {
			responseMessage.setData(sectionService.findAll(page, records));
		} catch (Exception e) {
			logger.error("SectionController -> getPage", e);
			responseMessage.setError(-1, "Unable to get page for Section: " + e.getMessage());
		}
		return responseMessage;
		
	}


//	@RequestMapping(value="/count", method=RequestMethod.GET,
//			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseMessage getCount() {
//
//		if (logger.isDebugEnabled())
//			logger.debug("SectionService -> getAll");
//		
//		ResponseMessage responseMessage = new ResponseMessage();
//		try {
//			responseMessage.setData(sectionService.count());
//		} catch (Exception e) {
//			logger.error("SectionController -> getAll", e);
//			responseMessage.setError(-1, "Unable to get all Section: " + e.getMessage());
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
//			logger.debug("SectionService -> getPage(" + page + "," + size + ")");
//		
//		ResponseMessage responseMessage = new ResponseMessage();
//		try {
//			responseMessage.setData(sectionService.getPage(page, size));
//		} catch (Exception e) {
//			logger.error("SectionController -> getAll", e);
//			responseMessage.setError(-1, "Unable to get all Section: " + e.getMessage());
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
//			logger.debug("SectionService -> getDropDownElements");
//		
//		ResponseMessage responseMessage = new ResponseMessage();
//		try {
//			responseMessage.setData(sectionService.getDropDownValues());
//		} catch (Exception e) {
//			logger.error("SectionController -> getDropDownElements", e);
//			responseMessage.setError(-1, "Unable to getDropDownElements for Section: " + e.getMessage());
//		}
//		return responseMessage;
//		
//	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public ResponseMessage getOne(@PathVariable Integer id) {
		if (logger.isDebugEnabled())
			logger.debug("SectionService -> getOne(" + id + ")");
		ResponseMessage responseMessage = new ResponseMessage();
		
		try {
			responseMessage.setData(sectionService.get(id));
		} catch (Exception e) {
			logger.error("SectionController -> create", e);
			responseMessage.setError(-1,
					"Unable to create Section: " + id + ",Error:"  + e.getMessage());
		}
		
		return responseMessage;
	}

/*	@RequestMapping(value="{section}/device/{device}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public ResponseMessage getByDeviceId(@PathVariable String section, @PathVariable String device) {
		if (logger.isDebugEnabled())
			logger.debug("SectionService -> findByDevice(" + device + ")");
		ResponseMessage responseMessage = new ResponseMessage();
		
		try {
			responseMessage.setData(sectionService.findByDevice(device));
		} catch (Exception e) {
			logger.error("SectionController -> create", e);
			responseMessage.setError(-1,
					"Unable to get sections for device: " + device + ",Error:"  + e.getMessage());
		}
		
		return responseMessage;
	}
*/

	
	/*
	 * DML Methods
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage create(@RequestBody Section element) {

		if (logger.isDebugEnabled())
			logger.debug("SectionService -> create:" + element);

		ResponseMessage responseMessage = new ResponseMessage();
		
		try {
			responseMessage.setData(sectionService.create(element));
		} catch (Exception e) {
			logger.error("SectionController -> create", e);
			responseMessage.setError(-1,
					"Unable to create Section: " + element + ",Error:"  + e.getMessage());
		}
		
		return responseMessage;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage edit(@PathVariable Integer id, @RequestBody Section element) {
		if (logger.isDebugEnabled())
			logger.debug("SectionService -> edit(" + id + "):" + element);

		ResponseMessage responseMessage = new ResponseMessage();
		
		try {
			element.setId(id);
			responseMessage.setData(sectionService.create(element));
		} catch (Exception e) {
			logger.error("SectionController -> edit", e);
			responseMessage.setError(-1,
					"Unable to edit Section: " + element + ",Error:" + e.getMessage());
		}
		
		return responseMessage;
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage delete(@PathVariable Integer id) {

		if (logger.isDebugEnabled())
			logger.debug("SectionService -> delete(" + id + ")");

		ResponseMessage responseMessage = new ResponseMessage();
		try {
			sectionService.delete(id);
		} catch (Exception e) {
			logger.error("SectionController -> delete", e);
			responseMessage.setError(-1, "Unable to delete Section: " + id + ",Error:"  + e.getMessage());
		}
		return responseMessage;
	}
	
}
