package org.timesheet.service.dao;

import org.junit.Test;
import org.timesheet.DomainAwareBase;
import org.timesheet.dao.EmployeeDao;
import org.timesheet.dao.ManagerDao;
import org.timesheet.dao.TaskDao;
import org.timesheet.dao.TimesheetDao;
import org.timesheet.domain.Employee;
import org.timesheet.domain.Manager;
import org.timesheet.domain.Task;
import org.timesheet.domain.Timesheet;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-12-12 Time: 下午11:42
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeDaoTest extends DomainAwareBase {

    @Resource
    private EmployeeDao employeeDao;

    @Resource
    private ManagerDao managerDao;

    @Resource
    private TaskDao taskDao;

    @Resource
    private TimesheetDao timesheetDao;

    @Test
    public void testAdd() {
        Employee employee = new Employee("test-employee", "hachzorz");
        employeeDao.add(employee);
        employee.setName("updated");

        employeeDao.update(employee);
        Employee found = employeeDao.find(employee.getId());
        assertEquals("updated", found.getName());
    }

    @Test
    public void testFind() {
        Employee employee = new Employee("test-employee", "hachzorz");
        employeeDao.add(employee);

        Employee found = employeeDao.find(employee.getId());
        assertEquals(found, employee);
    }

    @Test
    public void testList() {
        assertEquals(0, employeeDao.list().size());

        List<Employee> employees = Arrays.asList(new Employee("test-1", "testers"), new Employee("test-2", "testers"), new Employee("test-3", "testers"));
        for (Employee employee : employees) {
            employeeDao.add(employee);
        }

        List<Employee> found = employeeDao.list();
        assertEquals(3, found.size());
        for (Employee employee : employees) {
            assertTrue(employees.contains(employee));
        }
    }

    @Test
    public void testRemove() {
        Employee employee = new Employee("test-employee", "hackzorz");
        employeeDao.add(employee);

        assertEquals(employee, employeeDao.find(employee.getId()));

        employeeDao.remove(employee);
        assertNull(employeeDao.find(employee.getId()));
    }

    @Test
    public void testRemoveEmployee() {
        Manager manager = new Manager("task-manager");
        managerDao.add(manager);

        Employee employee = new Employee("Jaromir", "Hockey");
        employeeDao.add(employee);

        Task task = new Task("test-task", manager, employee);
        taskDao.add(task);

        Timesheet timesheet = new Timesheet(employee, task, 100);
        timesheetDao.add(timesheet);

        assertFalse(employeeDao.removeEmployee(employee));

        timesheetDao.remove(timesheet);
        taskDao.remove(task);
        assertTrue(employeeDao.removeEmployee(employee));
    }
}
