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

import com.heroku.demo.entities.Account;
import com.heroku.demo.exceptions.AccountNotFoundException;
import com.heroku.demo.repositories.AccountRepository;

/**
 * @author rtorres
 *
 */
@Service
@Transactional(rollbackFor=AccountNotFoundException.class)
public class AccountService 
{
	private static Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private MainService mainService;
	
	/*
	 * READ methods
	 */
	public Page<Account> findAll(int page, int count) {
		
		if (logger.isDebugEnabled())
			logger.debug("AccountService -> findAll Accounts");
		
		return accountRepository.findAll(new PageRequest(page, count));
	}

	public Account findBySfid(String account){
		if (logger.isDebugEnabled())
			logger.debug("Retrieving Account with sfid:" + account);
		
		return accountRepository.findBySfid(account);
		
	}

//	public List<Account> findByAccount(String account) {
//		if (logger.isDebugEnabled())
//			logger.debug("AccountService -> findByLink:" + account);
//
//		return accountRepository.findByAccount(account);
//	}
	public Account get(int id) {
		if (logger.isDebugEnabled())
			logger.debug("Retrieving Account with id:" + id);
		
		Account elementToGet = accountRepository.findOne(id);
		
		if (elementToGet == null)
			throw new AccountNotFoundException(id);
		
		return elementToGet;
	}

	/*
	 * 
	 */
	public Account create(Account account) {

		if (logger.isDebugEnabled())
			logger.debug("AccountService -> create:" + account);
			
		return accountRepository.save(account);
	}

	public Account update(Account account) {
		
		if (logger.isDebugEnabled())
			logger.debug("AccountService -> create:" + account);

		Account elementToUpdate = accountRepository.findOne(account.getId());
		
		if (elementToUpdate == null)
			throw new AccountNotFoundException(account.getName());
		
		elementToUpdate.update(account);
		accountRepository.save(elementToUpdate);
		
		return elementToUpdate;
	}

	public Account delete(int id) {
		if (logger.isDebugEnabled())
			logger.debug("AccountService -> delete:" + id);

		Account elementToDelete = get(id);
		
		if (elementToDelete == null)
			throw new AccountNotFoundException(id);
		
		accountRepository.delete(elementToDelete);

		return elementToDelete;
	}

	/*
	 * HELPERS
	 */

}

