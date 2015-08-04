/**
 * 
 */
package com.heroku.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heroku.demo.entities.Section;
import com.heroku.demo.exceptions.SectionNotFoundException;
import com.heroku.demo.repositories.SectionRepository;

/**
 * @author rtorres
 *
 */
@Service
@Transactional(rollbackFor=SectionNotFoundException.class)
public class SectionService 
{
	private static Logger logger = LoggerFactory.getLogger(SectionService.class);
	
	@Autowired
	private SectionRepository sectionRepository;
		
	/*
	 * READ methods
	 */
	public Page<Section> findAll(int page, int count) {
		
		if (logger.isDebugEnabled())
			logger.debug("SectionService -> findAll Sections");
		
		return sectionRepository.findAll(new PageRequest(page, count));
	}

	public Section get(int id) {
		if (logger.isDebugEnabled())
			logger.debug("Retrieving Section with id:" + id);
		
		Section elementToGet = sectionRepository.findOne(id);
		
		if (elementToGet == null)
			throw new SectionNotFoundException(id);
		
		return elementToGet;
	}

	/*
	 * 
	 */
	public Section create(Section section) {

		if (logger.isDebugEnabled())
			logger.debug("SectionService -> create:" + section);
			
		return sectionRepository.save(section);
	}

	public Section update(Section section) {
		
		if (logger.isDebugEnabled())
			logger.debug("SectionService -> create:" + section);

		Section elementToUpdate = sectionRepository.findOne(section.getId());
		
		if (elementToUpdate == null)
			throw new SectionNotFoundException(section.getName());
		
		elementToUpdate.update(section);
		sectionRepository.save(elementToUpdate);
		
		return elementToUpdate;
	}

	public Section delete(int id) {
		if (logger.isDebugEnabled())
			logger.debug("SectionService -> delete:" + id);

		Section elementToDelete = get(id);
		
		if (elementToDelete == null)
			throw new SectionNotFoundException(id);
		
		sectionRepository.delete(elementToDelete);

		return elementToDelete;
	}

	/*
	 * HELPERS
	 */

}

