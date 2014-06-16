package org.timesheet;

import javax.annotation.Resource;

import org.junit.Before;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.jdbc.JdbcTestUtils;

@ContextConfiguration(locations = "classpath*:persistence-beans.xml")
public abstract class DomainAwareBase extends AbstractJUnit4SpringContextTests {
    protected final String deleteScript = "./src/main/scripts/cleanup.sql";
    protected final String createScript = "./src/main/scripts/create-data.sql";

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Before
    public void deleteAllDomainEntities() {
        JdbcTestUtils.executeSqlScript(jdbcTemplate, new FileSystemResource(deleteScript), false);
    }

}







