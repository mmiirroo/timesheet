/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-4-14
 * description: 
 */

package org.learn.spring.dao.mapper;

import org.learn.spring.domain.Account;

public interface AccountMapper {
//    @Select("SELECT * FROM account WHERE id = #{accountId}")
//    Account getAccount(@Param("accountId") Long accountId );
    Account getAccount(Long accountId);
}
