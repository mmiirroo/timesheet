package org.timesheet.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.timesheet.DomainAwareBase;
import org.timesheet.dao.EmployeeDao;
import org.timesheet.dao.ManagerDao;
import org.timesheet.domain.Employee;
import org.timesheet.domain.Manager;
import org.timesheet.domain.Task;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-17
 * Time: 下午11:55
 * To change this template use File | Settings | File Templates.
 */

public class TimesheetServiceTest extends DomainAwareBase {
    @Resource
    private TimesheetService timesheetService;

    @Resource
    private EmployeeDao employeeDao;

    @Resource
    private ManagerDao managerDao;

    @Before
    public void insertData() {
        JdbcTestUtils.executeSqlScript(jdbcTemplate,
                new FileSystemResource(createScript), false);
    }

    @After
    public void cleanUp() {
        JdbcTestUtils.executeSqlScript(jdbcTemplate,
                new FileSystemResource(deleteScript), false);
    }

    @Test
    public void testBusiestTask() {
        Task task = timesheetService.busiestTask();
        assertTrue(1 == task.getId());
    }

    @Test
    public void testTasksForEmployees() {
        Employee steve = employeeDao.find(1L);
        Employee bill = employeeDao.find(2L);

        assertEquals(2, timesheetService.tasksForEmployee(steve).size());
        assertEquals(1, timesheetService.tasksForEmployee(bill).size());
    }

    @Test
    public void testTasksForManagers() {
        Manager eric = managerDao.find(1L);
        assertEquals(1, timesheetService.tasksForManager(eric).size());
    }
}
