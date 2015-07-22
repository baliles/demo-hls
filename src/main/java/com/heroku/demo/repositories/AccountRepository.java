package com.heroku.demo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.heroku.demo.entities.Account;

/**
 * Repository : Account.
 */
public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {
	public Account findBySfid(String sfid);
}
