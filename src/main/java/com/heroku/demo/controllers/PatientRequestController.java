package com.heroku.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.heroku.demo.entities.Account;
import com.heroku.demo.services.AccountService;

@Controller
@RequestMapping(value="maintenance/pr")
public class PatientRequestController extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(PatientRequestController.class);
	
	@Autowired
	private AccountService accountService;
	
	/*
	 * HTML Pages
	 */
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView listPage(@RequestParam(required = false, defaultValue = "0") int page, 
								 @RequestParam(required = false, defaultValue = "50") int records) {
		ModelAndView mav = new ModelAndView("maintenance/account/all");
		List<Account> Account = new ArrayList<Account>();
		
		if (logger.isDebugEnabled())
			logger.debug("AccountController -> Page for listing Account");
		
		Account.addAll(accountService.findAll(page, records).getContent());
		mav.addObject("solo","maintenance/");
		mav.addObject("Accounts", Account);
		return mav;
	}

	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView createPage() {
		ModelAndView mav = new ModelAndView("maintenance/account/new");
		mav.addObject("Account", new Account());
		
		if (logger.isDebugEnabled())
			logger.debug("AccountController -> Page for creating Account");
		
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}.html", method=RequestMethod.GET)
	public ModelAndView editPage(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("maintenance/account/edit");
		Account account = accountService.get(id);
		
		if (logger.isDebugEnabled())
			logger.debug("AccountController -> Page for editing Account");

		mav.addObject("Account", account);

		return mav;
	}

}
