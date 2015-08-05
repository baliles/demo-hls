package com.heroku.demo.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.heroku.demo.entities.Question;

/**
 * Repository : Account.
 */
public interface QuestionRepository extends PagingAndSortingRepository<Question, Integer> {
	public List<Question> findBySectionIdOrderByOrderAsc(int sectionId);
}
