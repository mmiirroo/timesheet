package org.timesheet.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.timesheet.DomainAwareBase;
import org.timesheet.dao.EmployeeDao;
import org.timesheet.domain.Employee;
import org.timesheet.web.exceptions.EmployeeDeleteException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-29
 * Time: 下午8:51
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration(locations = {"/persistence-beans.xml", "/controllers.xml"})
public class EmployeeControllerTest extends DomainAwareBase {
    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private EmployeeController controller;
    private Model model;

    @Before
    public void setUp() {
        model = new ExtendedModelMap();
    }

    @After
    public void tearDown() {
        List<Employee> employeeList = employeeDao.list();
        for (Employee employee : employeeList) {
            employeeDao.remove(employee);
        }
    }

    @Test
    public void testShowEmployees() {
        Employee employee = new Employee("Lucky", "Strike");
        employeeDao.add(employee);

        String view = controller.showEmployee(model);
        assertEquals("employees/list", view);

        List<Employee> listFromDao = employeeDao.list();
        Collection<?> listFromModel = (Collection<?>) model.asMap().get("employees");

        assertTrue(listFromModel.contains(employee));
        assertTrue(listFromDao.containsAll(listFromModel));
    }

    @Test
    public void testDeleteEmployeeOk() throws EmployeeDeleteException {
        Employee john = new Employee("John Lennon", "Singing");
        employeeDao.add(john);
        long id = john.getId();

        // delete & assert
        String view = controller.deleteEmployee(id);
        assertEquals("redirect:/employees", view);
        assertNull(employeeDao.find(id));
    }

    @Test(expected = EmployeeDeleteException.class)
    public void testDeleteEmployeeException() throws EmployeeDeleteException {
        Employee john = new Employee("John Lennon", "Singing");
        employeeDao.add(john);
        long id = john.getId();

        EmployeeDao mockedDao = mock(EmployeeDao.class);
        when(mockedDao.removeEmployee(john)).thenReturn(false);

        EmployeeDao originalDao = controller.getEmployeeDao();
        try {
            controller.setEmployeeDao(mockedDao);
            controller.deleteEmployee(id);
        } finally {
            controller.setEmployeeDao(originalDao);
        }
    }

    @Test
    public void testHandleDeleteException() {
        Employee john = new Employee("John Lennon", "Singing");
        EmployeeDeleteException e = new EmployeeDeleteException(john);

        ModelAndView modelAndView = controller.handleDeleteException(e);
        assertEquals("managers/delete-error", modelAndView.getViewName());
        assertTrue(modelAndView.getModelMap().containsValue(john));
    }

    @Test
    public void testGetEmployee() {
        // prepare employee
        Employee george = new Employee("George Harrison", "Singing");
        employeeDao.add(george);
        long id = george.getId();

        // get & assert
        String view = controller.getEmployee(id, model);
        assertEquals("employees/view", view);
        assertEquals(george, model.asMap().get("employee"));
    }

    @Test
    public void testUpdateEmployee() {
        Employee ringo = new Employee("Ringo Star", "Singing");
        employeeDao.add(ringo);
        long id = ringo.getId();

        ringo.setDepartment("Drums");

        String view = controller.updateEmployee(id, ringo);
        assertEquals("redirect:/employees", view);
        assertEquals("Drums", employeeDao.find(id).getDepartment());
    }

    @Test
    public void testAddEmployee() {
        Employee paul = new Employee("Paul McCartney", "Singing");

        String view = controller.addEmployee(paul);
        assertEquals("redirect:/employees", view);
        assertEquals(paul, employeeDao.find(paul.getId()));
    }
}
