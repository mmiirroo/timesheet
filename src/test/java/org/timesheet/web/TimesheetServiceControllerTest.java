package org.timesheet.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.timesheet.DomainAwareBase;
import org.timesheet.dao.EmployeeDao;
import org.timesheet.dao.ManagerDao;
import org.timesheet.domain.Employee;
import org.timesheet.domain.Manager;
import org.timesheet.service.TimesheetService;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

/**
 * User: seiyaa
 * Date: 14-1-11
 * Time: 下午5:16
 * Git: https://github.com/seiyaa
 */
@ContextConfiguration(locations = {"/persistence-beans.xml", "/controllers.xml"})
public class TimesheetServiceControllerTest extends DomainAwareBase {
    private final String createScript = "src/main/resources/sql/create-data.sql";
    @Resource
    private TimesheetServiceController controller;
    @Resource
    private TimesheetService timesheetService;
    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private ManagerDao managerDao;
    @Resource
    private JdbcTemplate jdbcTemplate;
    private Model model;

    @Before
    public void setUp() {
        model = new ExtendedModelMap();
        JdbcTestUtils.executeSqlScript(jdbcTemplate, new FileSystemResource(createScript), false);
    }

    @Test
    public void testShowMenu() {
        String view = controller.showMenu(model);
        assertEquals("timesheet-service/menu", view);
        assertEquals(timesheetService.busiestTask(), model.asMap().get("busiestTask"));

        assertEquals(employeeDao.list(), model.asMap().get("employees"));
        assertEquals(managerDao.list(), model.asMap().get("managers"));
    }

    @Test
    public void testShowManagerTaskd() {
        Manager manager = managerDao.list().get(0);
        long id = manager.getId();

        String view = controller.showManagerTasks(id, model);
        assertEquals("timesheet-service/manager-tasks", view);
        assertEquals(manager, model.asMap().get("manager"));
        assertEquals(timesheetService.tasksForManager(manager),
                model.asMap().get("tasks"));
    }

    @Test
    public void testShowEmployeeTasks() {
        Employee employee = employeeDao.list().get(0);
        long id = employee.getId();

        String view = controller.showEmployeeTasks(id, model);
        assertEquals("timesheet-service/employee-tasks", view);
        assertEquals(employee, model.asMap().get("employee"));
        assertEquals(timesheetService.tasksForEmployee(employee), model.asMap().get("tasks"));
    }

}
