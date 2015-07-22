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

import com.heroku.demo.entities.PatientRequest;
import com.heroku.demo.model.Register;
import com.heroku.demo.model.ResponseMessage;
import com.heroku.demo.services.PatientRequestService;

/*
 * REST Services
 */
@Controller
@RequestMapping(value="/api/v1/pr")
public class PatientRequestRestController {
	
	private static Logger logger = LoggerFactory.getLogger(PatientRequestController.class);
	
	@Autowired
	private PatientRequestService patientRequestService;
		
	/*
	 * READ METHODS
	 */

	
	@RequestMapping(value="/page", method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage getPage(@RequestParam int page, @RequestParam int records) {

		if (logger.isDebugEnabled())
			logger.debug("PatientRequestService -> getPage");
		
		ResponseMessage responseMessage = new ResponseMessage();
		try {
			responseMessage.setData(patientRequestService.findAll(page, records));
		} catch (Exception e) {
			logger.error("PatientRequestController -> getPage", e);
			responseMessage.setError(-1, "Unable to get page for PatientRequest: " + e.getMessage());
		}
		return responseMessage;
		
	}


//	@RequestMapping(value="/count", method=RequestMethod.GET,
//			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseMessage getCount() {
//
//		if (logger.isDebugEnabled())
//			logger.debug("PatientRequestService -> getAll");
//		
//		ResponseMessage responseMessage = new ResponseMessage();
//		try {
//			responseMessage.setData(patientRequestService.count());
//		} catch (Exception e) {
//			logger.error("PatientRequestController -> getAll", e);
//			responseMessage.setError(-1, "Unable to get all PatientRequest: " + e.getMessage());
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
//			logger.debug("PatientRequestService -> getPage(" + page + "," + size + ")");
//		
//		ResponseMessage responseMessage = new ResponseMessage();
//		try {
//			responseMessage.setData(patientRequestService.getPage(page, size));
//		} catch (Exception e) {
//			logger.error("PatientRequestController -> getAll", e);
//			responseMessage.setError(-1, "Unable to get all PatientRequest: " + e.getMessage());
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
//			logger.debug("PatientRequestService -> getDropDownElements");
//		
//		ResponseMessage responseMessage = new ResponseMessage();
//		try {
//			responseMessage.setData(patientRequestService.getDropDownValues());
//		} catch (Exception e) {
//			logger.error("PatientRequestController -> getDropDownElements", e);
//			responseMessage.setError(-1, "Unable to getDropDownElements for PatientRequest: " + e.getMessage());
//		}
//		return responseMessage;
//		
//	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public ResponseMessage getOne(@PathVariable Integer id) {
		if (logger.isDebugEnabled())
			logger.debug("PatientRequestService -> getOne(" + id + ")");
		ResponseMessage responseMessage = new ResponseMessage();
		
		try {
			responseMessage.setData(patientRequestService.get(id));
		} catch (Exception e) {
			logger.error("PatientRequestController -> create", e);
			responseMessage.setError(-1,
					"Unable to create PatientRequest: " + id + ",Error:"  + e.getMessage());
		}
		
		return responseMessage;
	}

/*	@RequestMapping(value="{patientRequest}/device/{device}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public ResponseMessage getByDeviceId(@PathVariable String patientRequest, @PathVariable String device) {
		if (logger.isDebugEnabled())
			logger.debug("PatientRequestService -> findByDevice(" + device + ")");
		ResponseMessage responseMessage = new ResponseMessage();
		
		try {
			responseMessage.setData(patientRequestService.findByDevice(device));
		} catch (Exception e) {
			logger.error("PatientRequestController -> create", e);
			responseMessage.setError(-1,
					"Unable to get patientRequests for device: " + device + ",Error:"  + e.getMessage());
		}
		
		return responseMessage;
	}
*/

	/*
	 * DML Methods
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage create(@RequestBody PatientRequest element) {

		if (logger.isDebugEnabled())
			logger.debug("PatientRequestService -> create:" + element);

		ResponseMessage responseMessage = new ResponseMessage();
		
		try {
			responseMessage.setData(patientRequestService.create(element));
		} catch (Exception e) {
			logger.error("PatientRequestController -> create", e);
			responseMessage.setError(-1,
					"Unable to create PatientRequest: " + element + ",Error:"  + e.getMessage());
		}
		
		return responseMessage;
	}

	@RequestMapping(value = "/request", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage request(@RequestBody Register element) {

		if (logger.isDebugEnabled())
			logger.debug("PatientRequestService -> create:" + element);

		ResponseMessage responseMessage = new ResponseMessage();
		PatientRequest pr = new PatientRequest(element);
		
		try {
			pr = patientRequestService.create(pr);
			responseMessage.setData(element);
		} catch (Exception e) {
			logger.error("PatientRequestController -> create", e);
			responseMessage.setError(-1,
					"Unable to create PatientRequest: " + element + ",Error:"  + e.getMessage());
		}
		
		return responseMessage;
	}


	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage edit(@PathVariable Integer id, @RequestBody PatientRequest element) {
		if (logger.isDebugEnabled())
			logger.debug("PatientRequestService -> edit(" + id + "):" + element);

		ResponseMessage responseMessage = new ResponseMessage();
		
		try {
			element.setId(id);
			responseMessage.setData(patientRequestService.create(element));
		} catch (Exception e) {
			logger.error("PatientRequestController -> edit", e);
			responseMessage.setError(-1,
					"Unable to edit PatientRequest: " + element + ",Error:" + e.getMessage());
		}
		
		return responseMessage;
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage delete(@PathVariable Integer id) {

		if (logger.isDebugEnabled())
			logger.debug("PatientRequestService -> delete(" + id + ")");

		ResponseMessage responseMessage = new ResponseMessage();
		try {
			patientRequestService.delete(id);
		} catch (Exception e) {
			logger.error("PatientRequestController -> delete", e);
			responseMessage.setError(-1, "Unable to delete PatientRequest: " + id + ",Error:"  + e.getMessage());
		}
		return responseMessage;
	}
	
}
