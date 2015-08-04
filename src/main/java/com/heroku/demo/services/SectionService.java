/**
 * 
 */
package com.heroku.demo.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heroku.demo.entities.Answer;
import com.heroku.demo.entities.Question;
import com.heroku.demo.entities.Section;
import com.heroku.demo.exceptions.SectionNotFoundException;
import com.heroku.demo.repositories.AnswerRepository;
import com.heroku.demo.repositories.QuestionRepository;
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
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	/*
	 * READ methods
	 */
	public Page<Section> findAll(int page, int count) {
		
		if (logger.isDebugEnabled())
			logger.debug("SectionService -> findPage Sections");
		
		Page<Section> ss = sectionRepository.findAll(new PageRequest(page, count));
		
		for (Section s: ss)
			s.setQuestions(getQuestionsForSection(s));
		
		return ss;
	}

	public Iterable<Section> findAll() {
		
		if (logger.isDebugEnabled())
			logger.debug("SectionService -> findAll Sections");
		
		Iterable<Section> ss = sectionRepository.findAll(sortBySectionOrder());
		
		for (Section s: ss)
			s.setQuestions(getQuestionsForSection(s));
		
		return ss;
	}

	public Section findByName(String name) {
		if (logger.isDebugEnabled())
			logger.debug("Retrieving Section with id:" + name);
		
		Section elementToGet = sectionRepository.findByName(name);
		
		if (elementToGet == null)
			throw new SectionNotFoundException(name);
		
		elementToGet.setQuestions(getQuestionsForSection(elementToGet));

		return elementToGet;
	}

	
	public Section get(int id) {
		if (logger.isDebugEnabled())
			logger.debug("Retrieving Section with id:" + id);
		
		Section elementToGet = sectionRepository.findOne(id);
		
		if (elementToGet == null)
			throw new SectionNotFoundException(id);
		
		return elementToGet;
	}

	
	private List<Question> getQuestionsForSection(Section s){
		List<Question> qs = questionRepository.findBySectionId(s.getId());

		for (int x=0 ; x < qs.size() ; x++){
			qs.get(x).setAnswers(getAnswersForQuestion(qs.get(x)));
		}
		
		return qs;
	}
	
	private List<Answer> getAnswersForQuestion(Question q){
		return answerRepository.findByQuestionId(q.getId());
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
	private Sort sortBySectionOrder() {
		return new Sort(Sort.Direction.ASC, "order");
	}

}

