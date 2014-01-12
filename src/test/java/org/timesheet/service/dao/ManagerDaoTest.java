package org.timesheet.service.dao;

import org.junit.Test;
import org.timesheet.DomainAwareBase;
import org.timesheet.domain.Employee;
import org.timesheet.domain.Manager;
import org.timesheet.domain.Task;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ManagerDaoTest extends DomainAwareBase {
    @Resource
    private EmployeeDao employeeDao;

    @Resource
    private ManagerDao managerDao;

    @Resource
    private TaskDao taskDao;

    @Test
    public void testAdd() {
        int size = managerDao.list().size();
        managerDao.add(new Manager("test-manager"));
        assertTrue(size < managerDao.list().size());
    }

    @Test
    public void testUpdate() {
        Manager manager = new Manager("test-manager-update");
        managerDao.add(manager);
        manager.setName("updated");

        managerDao.update(manager);
        Manager found = managerDao.find(manager.getId());
        assertEquals(found, manager);
    }

    @Test
    public void testFind() {
        Manager manager = new Manager("test-manager");
        managerDao.add(manager);

        Manager found = managerDao.find(manager.getId());
        assertEquals(manager, found);
    }

    @Test
    public void testList() {
        assertEquals(0, managerDao.list().size());

        List<Manager> managers = Arrays.asList(
                new Manager("test-1"),
                new Manager("test-2"),
                new Manager("test-3")
        );
        for (Manager manager : managers) {
            managerDao.add(manager);
        }

        List<Manager> found = managerDao.list();
        assertEquals(3, found.size());
        for (Manager manager : found) {
            assertTrue(managers.contains(manager));
        }
    }

    @Test
    public void testRemove() {
        Manager manager = new Manager("test-manager");
        managerDao.add(manager);

        assertEquals(manager, managerDao.find(manager.getId()));

        managerDao.remove(manager);
        assertNull(managerDao.find(manager.getId()));
    }

    @Test
    public void testRemoveManager() {
        Manager manager = new Manager("task-manager");
        managerDao.add(manager);

        Employee employee = new Employee("Jaromir", "Hockey");
        employeeDao.add(employee);

        Task task = new Task("test-task", manager, employee);
        taskDao.add(task);

        assertFalse(managerDao.removeManager(manager));

        taskDao.remove(task);
        assertTrue(employeeDao.removeEmployee(employee));
    }
}
