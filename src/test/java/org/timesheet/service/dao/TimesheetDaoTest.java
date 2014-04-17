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
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-15
 * Time: 上午12:39
 * To change this template use File | Settings | File Templates.
 */

public class TimesheetDaoTest extends DomainAwareBase {
    @Resource
    private TimesheetDao timesheetDao;
    @Resource
    private TaskDao taskDao;
    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private ManagerDao managerDao;
    private Task task;
    private Employee employee;

    @Override
    public void deleteAllDomainEntities() {
        super.deleteAllDomainEntities();
        setUp();
    }

    public void setUp() {
        employee = new Employee("Steve", "Engineering");
        employeeDao.add(employee);

        Manager manager = new Manager("Bob");
        managerDao.add(manager);

        task = new Task("Learn Spring", manager, employee);
        taskDao.add(task);
    }

    @Test
    public void testAdd() {
        int size = timesheetDao.list().size();

        Timesheet timesheet = newTimesheet();
        timesheetDao.add(timesheet);

        assertTrue(size < timesheetDao.list().size());
    }

    @Test
    public void testUpdate() {
        Timesheet timesheet = newTimesheet();
        timesheetDao.add(timesheet);

        timesheet.setHours(6);
        taskDao.update(timesheet.getTask());
        timesheetDao.update(timesheet);

        Timesheet found = timesheetDao.find(timesheet.getId());
        assertTrue(6 == found.getHours());
    }

    @Test
    public void testFind() {
        Timesheet timesheet = newTimesheet();
        timesheetDao.add(timesheet);

        assertEquals(timesheet, timesheetDao.find(timesheet.getId()));
    }

    @Test
    public void testList() {
        assertEquals(0, timesheetDao.list().size());
        Timesheet templateTimesheet = newTimesheet();

        List<Timesheet> timesheetList = Arrays.asList(
                newTimesheetFromTemplate(templateTimesheet, 4),
                newTimesheetFromTemplate(templateTimesheet, 7),
                newTimesheetFromTemplate(templateTimesheet, 10)
        );

        for (Timesheet timesheet : timesheetList) {
            timesheetDao.add(timesheet);
        }

        List<Timesheet> foundList = timesheetDao.list();
        assertEquals(3, foundList.size());
        for (Timesheet timesheet : foundList) {
            assertTrue(timesheetList.contains(timesheet));
        }
    }

    private Timesheet newTimesheetFromTemplate(Timesheet template, int hours) {
        return new Timesheet(template.getWho(), template.getTask(), hours);
    }

    @Test
    public void testRemove() {
        Timesheet timesheet = newTimesheet();
        timesheetDao.add(timesheet);

        assertEquals(timesheet, timesheetDao.find(timesheet.getId()));

        timesheetDao.remove(timesheet);
        assertNull(timesheetDao.find(timesheet.getId()));
    }

    private Timesheet newTimesheet() {
        return new Timesheet(employee, task, 5);
    }
}
