package org.learn.spring.service;

import org.learn.spring.domain.Account;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

@ContextConfiguration("classpath*:spring-db.xml")
@Transactional
@ActiveProfiles("test")
public class AccountServiceTestNGTest extends AbstractTransactionalTestNGSpringContextTests {
    @Resource
    private AccountService accountService;

    @org.testng.annotations.Test
    public void testGetAccountById() {
        Account acct = new Account(1, "user01", 18, "M");
        accountService.insertIfNotExist(acct);
        Account acct2 = accountService.getAccountById(1);
        assertEquals(acct, acct2);
    }
}
