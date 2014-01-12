package org.timesheet.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.timesheet.domain.Employee;
import org.timesheet.service.GenericDao;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class EmployeeDaoTest {
    private GenericDao<Employee, Long> employeeDao = new InMemoryDao<Employee, Long>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            Employee e = new Employee("Mike" + i, "IT");
            employeeDao.add(e);
        }
    }

    @Test
    public void testAdd() {
        int oldSize = employeeDao.list().size();
        Employee e = employeeDao.find(1L);
        employeeDao.remove(e);
        int newSize = employeeDao.list().size();
        assertFalse(oldSize == newSize);
    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testList() {
        List<Employee> list = employeeDao.list();
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }
}
