package org.learn.spring.service;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.learn.spring.domain.Account;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-db.xml")
@Transactional
@ActiveProfiles("test")
public class AccountServiceTest
{
    @Resource
    private AccountService accountService;

    @Test
    public void testGetAccountById()
    {
        Account acct = new Account(1, "user01", 18, "M");
        accountService.insertIfNotExist(acct);
        Account acct2 = accountService.getAccountById(1);
        assertEquals(acct, acct2);
    }

    @Test(timeout = 3000)
    public void testHugeJob()
    {
        accountService.doSomeHugeJob();
    }

    @Repeat(3)
    @Test(expected = IllegalArgumentException.class)
    public void testInsertException()
    {
        accountService.insertIfNotExist(null);
    }
}