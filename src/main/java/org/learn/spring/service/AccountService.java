package org.learn.spring.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.learn.spring.dao.AccountDao;
import org.learn.spring.domain.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "accountService")
public class AccountService {
    protected final Log LOG = LogFactory.getLog(getClass());

    @Resource
    private AccountDao accountDao;

    public Account getAccountById(int id) {
        return accountDao.getAccountById(id);
    }

    public void insertIfNotExist(Account account) {
        if (account == null)
            throw new IllegalArgumentException("account is null");
        Account acct = accountDao.getAccountById(account.getId());
        if (acct == null) {
            LOG.debug("No " + account + " found,would insert it.");
        }
        accountDao.saveAccount(account);
        acct = null;
    }

    public boolean removeAccount(Account acct) {
        return accountDao.removeAccount(acct);
    }

    public void doSomeHugeJob() {
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
        }
    }
}
