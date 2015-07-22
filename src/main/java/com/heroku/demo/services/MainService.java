/**
 * 
 */
package com.heroku.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rtorres
 *
 */
@Service
@Transactional
public class MainService 
{
	private static Logger logger = LoggerFactory.getLogger(MainService.class);
	
	public MainService(){
		super();
	}
	
	public void log(String log){
		logger.info("MainService -> " + log);
	}
	
}

