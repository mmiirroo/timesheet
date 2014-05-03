package org.learn.spring.cacheofAnno;

import org.learn.spring.domain.Account;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public class AccountService {
	@Cacheable(value = "accountCache",condition="#name.length() <= 4", key="#userName.concat(#password)")
	public Account getAccountByName(String name,String password,boolean sendLog) {
		System.out.println("real query account:" + name);
		return getFromDB(name);
	}

	@CacheEvict(value = "accountCache", key = "#account.getName()")
	public void updateAccount(Account account) {
		updateDB(account);
	}

	@CacheEvict(value = "accountCache", allEntries = true)
	public void reload() {
	}

	private void updateDB(Account account) {
		System.out.println("real update db..." + account.getName());
	}

	public Account getFromDB(String name) {
		System.out.println("real query db:" + name);
		return new Account(name);
	}
}
