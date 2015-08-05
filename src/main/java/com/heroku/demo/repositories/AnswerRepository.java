package com.heroku.demo.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.heroku.demo.entities.Answer;

/**
 * Repository : Account.
 */
public interface AnswerRepository extends PagingAndSortingRepository<Answer, Integer> {
	public List<Answer> findByQuestionId(int questionId);
}
