/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-4-30
 */

package mybatis;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.learn.spring.dao.mapper.AccountMapper;
import org.learn.spring.domain.Account;

public class TestUser {
    private SqlSession session = null;
    private Logger log = Logger.getLogger(getClass().getName());
    private long executionStartTime;
    private long executionEndTime;

    public TestUser() {
    }

    public static void main(String[] args) {
        new TestUser().run(args);
    }

    private void run(String[] args) {
        try {
            executionStartTime = System.currentTimeMillis();
            String aResource = "mybatis/mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(aResource);
            SqlSessionFactory aSessionFactory = new SqlSessionFactoryBuilder().build(reader);

            log.info(aSessionFactory.getConfiguration().getEnvironment().getDataSource().toString());
            session = aSessionFactory.openSession();

            log.info(session.getConfiguration().getEnvironment().getDataSource().toString());
            AccountMapper mapper = session.getMapper(AccountMapper.class);
            log.info("Got mapper");
            List<Account> users = mapper.selectAll();
            executionEndTime = System.currentTimeMillis();
            log.info("Time taken to execute in seconds : " + ((this.executionEndTime - this.executionStartTime) / 1000) + " .. for results "
                    + users.size());
        }

        catch (Exception e) {
            log.error("Exception caught: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
