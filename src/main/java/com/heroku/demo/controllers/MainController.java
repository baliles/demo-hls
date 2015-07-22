package com.heroku.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.heroku.demo.model.ResponseMessage;
import com.heroku.demo.model.SimpleRecord;
import com.heroku.demo.services.MainService;

@Controller
@RequestMapping(value="/main")
public class MainController {
	
	private static Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private MainService mainService;
	
	/*
	 * HTML Pages
	 */
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView all() {
		ModelAndView mav = new ModelAndView("main");
		
		if (logger.isDebugEnabled())
			logger.debug("MainController -> all -> main");
		
		return mav;
	}

	/*
	 * REST Services
	 */
	
	/*
	 * READ METHODS
	 */
	@RequestMapping(value="", method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage getAll() {

		ResponseMessage responseMessage = new ResponseMessage();
		try {
			responseMessage.setData(new SimpleRecord("Status", "OK!"));
		} catch (Exception e) {
			logger.error("MainController -> getSessionInfo", e);
			responseMessage.setError(-1, "Unable to get SessionInfo: " + e.getMessage());
		}
		return responseMessage;
		
	}
	
}
