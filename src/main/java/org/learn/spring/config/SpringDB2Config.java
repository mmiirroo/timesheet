package org.learn.spring.config;

import org.learn.spring.dao.AccountDao;
import org.learn.spring.service.AccountService;
import org.learn.spring.service.Initializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;

@Configuration
public class SpringDB2Config {
    @Resource
    private DriverManagerDataSource datasource;

    @Bean
    public Initializer initer() {
        return new Initializer();
    }

    @Bean
    public AccountDao accountDao() {
        AccountDao accountDao = new AccountDao();
        accountDao.setDataSource(datasource);
        return accountDao;
    }

    @Bean
    public AccountService accountService() {
        return new AccountService();
    }
}