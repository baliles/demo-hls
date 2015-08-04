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

import com.heroku.demo.entities.Section;
import com.heroku.demo.services.SectionService;

@Controller
@RequestMapping(value="maintenance/sections")
public class SectionController extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(SectionController.class);
	
	@Autowired
	private SectionService sectionService;
	
	/*
	 * HTML Pages
	 */
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView listPage(@RequestParam(required = false, defaultValue = "0") int page, 
								 @RequestParam(required = false, defaultValue = "50") int records) {
		ModelAndView mav = new ModelAndView("maintenance/section/all");
		List<Section> Section = new ArrayList<Section>();
		
		if (logger.isDebugEnabled())
			logger.debug("SectionController -> Page for listing Section");
		
		Section.addAll(sectionService.findAll(page, records).getContent());
		mav.addObject("solo","maintenance/");
		mav.addObject("Sections", Section);
		return mav;
	}

	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView createPage() {
		ModelAndView mav = new ModelAndView("maintenance/section/new");
		mav.addObject("Section", new Section());
		
		if (logger.isDebugEnabled())
			logger.debug("SectionController -> Page for creating Section");
		
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}.html", method=RequestMethod.GET)
	public ModelAndView editPage(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("maintenance/section/edit");
		Section section = sectionService.get(id);
		
		if (logger.isDebugEnabled())
			logger.debug("SectionController -> Page for editing Section");

		mav.addObject("Section", section);

		return mav;
	}

}
