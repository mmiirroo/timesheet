package org.learn.spring.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.learn.spring.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AccountServiceOldTest
{
    private static AccountService accountService;

    @BeforeClass
    public static void init()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring-db-old.xml");
        accountService = (AccountService) context.getBean("accountService");
    }

    @Test
    public void testGetAccountById()
    {
        Account acct = new Account(1, "uesr", 10, "M");
        Account acct2 = null;
        try
        {
            accountService.insertIfNotExist(acct);
            acct2 = accountService.getAccountById(1);
            assertEquals(acct, acct2);
        }
        catch (Exception e)
        {
            fail(e.getMessage());
        }
        finally
        {
            accountService.removeAccount(acct);
        }
    }
}
