package org.timesheet.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.timesheet.DomainAwareBase;
import org.timesheet.domain.Employee;
import org.timesheet.domain.Manager;
import org.timesheet.domain.Task;
import org.timesheet.service.dao.EmployeeDao;
import org.timesheet.service.dao.ManagerDao;
import org.timesheet.service.dao.TaskDao;
import org.timesheet.web.exceptions.TaskDeleteException;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * User: seiyaa
 * Date: 14-1-11
 * Time: 上午9:55
 * Git: https://github.com/seiyaa
 */
@ContextConfiguration(locations = {"/persistence-beans.xml", "/controllers.xml"})
public class TaskControllerTest extends DomainAwareBase {
    private Model model;
    @Resource
    private TaskDao taskDao;
    @Resource
    private ManagerDao managerDao;
    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private TaskController controller;

    @Before
    public void setUp() {
        model = new ExtendedModelMap();
    }

    @After
    public void tearDown() {
        List<Task> tasks = taskDao.list();
        for (Task task : tasks) {
            taskDao.remove(task);
        }
    }

    @Test
    public void testShowTasks() {
        Task task = sampleTask();
        String view = controller.showTasks(model);
        assertEquals("tasks/list", view);

        List<Task> listFromDao = taskDao.list();
        Collection<?> listFromModel = (Collection<?>) model.asMap().get("tasks");

        assertTrue(listFromModel.contains(task));
        assertTrue(listFromDao.containsAll(listFromModel));
    }

    @Test
    public void testDeleteOk() throws TaskDeleteException {
        Task task = sampleTask();
        long id = task.getId();

        String view = controller.deleteTask(id);
        assertEquals("redirect:/tasks", view);
        assertNull(taskDao.find(id));
    }

    @Test(expected = TaskDeleteException.class)
    public void testDeleteTaskThrowsException() {
        Task task = sampleTask();
        long id = task.getId();

        TaskDao mockedDao = mock(TaskDao.class);
        when(mockedDao.removeTask(task)).thenReturn(false);

        TaskDao originalDao = controller.getTaskDao();
        try {
            controller.setTaskDao(mockedDao);
            controller.deleteTask(id);
        } catch (TaskDeleteException e) {
            controller.setTaskDao(originalDao);
        }
    }

    @Test
    public void testHandlerDeleteException() {
        Task task = sampleTask();
        TaskDeleteException e = new TaskDeleteException(task);
        ModelAndView modelAndView = controller.handleDeleteException(e);

        assertEquals("tasks/delete-error", modelAndView.getViewName());
        assertTrue(modelAndView.getModelMap().containsValue(task));
    }

    @Test
    public void testGetTask() {
        Task task = sampleTask();
        long id = task.getId();

        String view = controller.getTask(id, model);
        assertEquals("tasks/view", view);
        assertEquals(task, model.asMap().get("task"));
    }

    @Test
    public void testRemoveEmployee() {
        Task task = sampleTask();
        long id = task.getAssignedEmployees().get(0).getId();
        controller.removeEmployee(task.getId(), id);

        task = taskDao.find(task.getId());
        Employee empployee = employeeDao.find(id);
        assertFalse(task.getAssignedEmployees().contains(empployee));
    }

    @Test
    public void testAddEmployee() {
        Task task = sampleTask();
        Employee cassidy = new Employee("Butch Cassidy", "Cowboys");

        employeeDao.add(cassidy);
        controller.addEmployee(task.getId(), cassidy.getId());

        task = taskDao.find(task.getId());

        Employee employee = employeeDao.find(cassidy.getId());
        assertTrue(task.getAssignedEmployees().contains(employee));
    }

    @Test
    public void testAddTask() {
        Task task = sampleTask();
        String view = controller.addTask(task);
        assertEquals("redirect:/tasks", view);
        assertEquals(task, taskDao.find(task.getId()));
    }

    private Task sampleTask() {
        Manager manager = new Manager("Jesse James");
        managerDao.add(manager);

        Employee terrence = new Employee("Terrence", "Cowboys");
        Employee kid = new Employee("Sundance Kid", "Cowboys");
        employeeDao.add(terrence);
        employeeDao.add(kid);

        Task task = new Task("Wild West", manager, terrence, kid);
        taskDao.add(task);
        return task;
    }
}
