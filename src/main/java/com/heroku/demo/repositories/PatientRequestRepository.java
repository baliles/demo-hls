package com.heroku.demo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.heroku.demo.entities.PatientRequest;



/**
 * Spring JPA Repository for PatientRequest
 * 
 * @author rtorres
 *
 */
public interface PatientRequestRepository extends PagingAndSortingRepository<PatientRequest, Integer> {

	public PatientRequest findBySfid(String sfid);
	public Iterable<PatientRequest> findByCommentsCLikeAndPatientNameC(String like, String sfid);
}