package com.heroku.demo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.heroku.demo.entities.Section;

/**
 * Repository : Account.
 */
public interface SectionRepository extends PagingAndSortingRepository<Section, Integer> {
	
	public Section findByName(String name);
}
