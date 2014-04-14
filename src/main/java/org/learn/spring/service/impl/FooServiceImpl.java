/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-4-14
 * description: 
 */

package org.learn.spring.service.impl;

import org.learn.spring.dao.mapper.AccountMapper;
import org.learn.spring.domain.Account;
import org.learn.spring.service.FooService;

public class FooServiceImpl implements FooService {

    private AccountMapper accountMapper;

    public void setUserMapper(AccountMapper userMapper) {
        this.accountMapper = userMapper;
    }

    public Account doSomeBusinessStuff(String userId) {
        return this.accountMapper.getAccount(userId);
    }
}
