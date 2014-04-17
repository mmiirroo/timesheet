package org.timesheet.service.dao;

import org.junit.Test;
import org.timesheet.DomainAwareBase;
import org.timesheet.dao.EmployeeDao;
import org.timesheet.dao.ManagerDao;
import org.timesheet.dao.TaskDao;
import org.timesheet.domain.Employee;
import org.timesheet.domain.Manager;
import org.timesheet.domain.Task;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-15
 * Time: 上午12:00
 * To change this template use File | Settings | File Templates.
 */

public class TaskDaoTest extends DomainAwareBase {
    @Resource
    private ManagerDao managerDao;
    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private TaskDao taskDao;

    @Test
    public void testAdd() {
        int size = taskDao.list().size();
        taskDao.add(newSpringTask());

        assertTrue(size < taskDao.list().size());
    }

    @Test
    public void testUpdate() {
        Task task = newSpringTask();
        taskDao.add(task);

        String desc = "learn spring 3.1";
        task.setDescription(desc);
        taskDao.update(task);

        Task found = taskDao.find(task.getId());
        assertEquals(desc, found.getDescription());
    }

    @Test
    public void testFind() {
        Task task = newSpringTask();
        taskDao.add(task);
        Task foundTask = taskDao.find(task.getId());
        assertEquals(task, foundTask);
    }

    @Test
    public void testList() {
        assertEquals(0, taskDao.list().size());
        Task templateTask = newSpringTask();

        List<Task> taskList = Arrays.asList(
                newTaskFromTemplate(templateTask, "1"),
                newTaskFromTemplate(templateTask, "2"),
                newTaskFromTemplate(templateTask, "3")
        );
        for (Task task : taskList) {
            taskDao.add(task);
        }

        List<Task> found = taskDao.list();
        assertEquals(3, found.size());
        for (Task task : found) {
            assertTrue(taskList.contains(task));
        }
    }

    @Test
    public void testRemove() {
        Task task = newSpringTask();
        taskDao.add(task);

        assertEquals(task, taskDao.find(task.getId()));
        taskDao.remove(task);
        assertNull(taskDao.find(task.getId()));
    }

    /**
     * @return Dummy task for testing
     */
    private Task newSpringTask() {
        Manager bob = new Manager("Bob");
        managerDao.add(bob);

        Employee steve = new Employee("Steve", "Business");
        Employee woz = new Employee("Woz", "Engineering");
        employeeDao.add(steve);
        employeeDao.add(woz);

        return new Task("Learn Spring", bob, steve, woz);
    }

    /**
     * Creates dummy task fo testing as copy of existing task and
     * adds aditional information to every field.
     *
     * @param templateTask Task to copy
     * @param randomInfo   Info to append everywhere
     * @return Random task for testing
     */
    private Task newTaskFromTemplate(Task templateTask,
                                     String randomInfo) {
        String description = templateTask.getDescription()
                + randomInfo;

        Manager manager = new Manager(
                templateTask.getManager().getName());
        managerDao.add(manager);

        List<Employee> templateEmployees = templateTask.getAssignedEmployees();
        Employee[] employees = new Employee[templateEmployees.size()];

        int idx = 0;
        for (Employee templateEmployee : templateEmployees) {
            Employee employee = new Employee(
                    templateEmployee.getName() + randomInfo,
                    templateEmployee.getDepartment() + randomInfo);
            employees[idx++] = employee;
            employeeDao.add(employee);
        }

        return new Task(description, manager, employees);
    }
}
