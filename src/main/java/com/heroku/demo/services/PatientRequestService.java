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

import com.heroku.demo.entities.PatientRequest;
import com.heroku.demo.exceptions.PatientRequestNotFoundException;
import com.heroku.demo.repositories.PatientRequestRepository;

/**
 * @author rtorres
 *
 */
@Service
@Transactional(rollbackFor=PatientRequestNotFoundException.class)
public class PatientRequestService 
{
	private static Logger logger = LoggerFactory.getLogger(PatientRequestService.class);
	
	@Autowired
	private PatientRequestRepository patientRequestRepository;
	
	/*
	 * READ methods
	 */
	public Page<PatientRequest> findAll(int page, int count) {
		
		if (logger.isDebugEnabled())
			logger.debug("PatientRequestService -> findAll PatientRequest");
		
		return patientRequestRepository.findAll(new PageRequest(page, count));
	}

	public PatientRequest findBySfid(String patientRequest){
		if (logger.isDebugEnabled())
			logger.debug("Retrieving PatientRequest with sfid:" + patientRequest);
		
		return patientRequestRepository.findBySfid(patientRequest);
		
	}

	public Iterable<PatientRequest> findLikeComment(String patientRequest){
		if (logger.isDebugEnabled())
			logger.debug("Retrieving PatientRequest with sfid:" + patientRequest);
		
		return patientRequestRepository.findByCommentsCLikeAndPatientNameC("COMMENT:%", patientRequest);
	}
	
	
//	public List<PatientRequest> findByPatientRequest(String patientRequest) {
//		if (logger.isDebugEnabled())
//			logger.debug("PatientRequestService -> findByLink:" + patientRequest);
//
//		return patientRequestRepository.findByPatientRequest(patientRequest);
//	}
	public PatientRequest get(int id) {
		if (logger.isDebugEnabled())
			logger.debug("Retrieving PatientRequest with id:" + id);
		
		PatientRequest elementToGet = patientRequestRepository.findOne(id);
		
		if (elementToGet == null)
			throw new PatientRequestNotFoundException(id);
		
		return elementToGet;
	}

	/*
	 * 
	 */
	public PatientRequest create(PatientRequest patientRequest) {

		if (logger.isDebugEnabled())
			logger.debug("PatientRequestService -> create:" + patientRequest);
			
		return patientRequestRepository.save(patientRequest);
	}

	public PatientRequest update(PatientRequest patientRequest) {
		
		if (logger.isDebugEnabled())
			logger.debug("PatientRequestService -> create:" + patientRequest);

		PatientRequest elementToUpdate = patientRequestRepository.findOne(patientRequest.getId());
		
		if (elementToUpdate == null)
			throw new PatientRequestNotFoundException(patientRequest.getName());
		
		elementToUpdate.update(patientRequest);
		patientRequestRepository.save(elementToUpdate);
		
		return elementToUpdate;
	}

	public PatientRequest delete(int id) {
		if (logger.isDebugEnabled())
			logger.debug("PatientRequestService -> delete:" + id);

		PatientRequest elementToDelete = get(id);
		
		if (elementToDelete == null)
			throw new PatientRequestNotFoundException(id);
		
		patientRequestRepository.delete(elementToDelete);

		return elementToDelete;
	}

	/*
	 * HELPERS
	 */

}

