/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-4-16
 */

package mybatis;

import org.junit.Assert;
import org.junit.Test;
import org.learn.spring.dao.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = "classpath*:persistence-beans.xml")
public class TestAccountMapper extends AbstractJUnit4SpringContextTests {

    @Autowired
    public AccountMapper accountMapper;

    @Test
    public void testGetAccount() {
        Assert.assertNotNull(accountMapper.getAccount(300L));
    }
}