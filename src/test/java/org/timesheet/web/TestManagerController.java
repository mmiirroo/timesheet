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
import org.timesheet.dao.ManagerDao;
import org.timesheet.domain.Manager;
import org.timesheet.web.exceptions.ManagerDeleteException;

@ContextConfiguration(locations = {"/persistence-beans.xml", "/controllers.xml"})
public class TestManagerController extends DomainAwareBase {
    @Resource
    private ManagerDao managerDao;
    @Resource
    private ManagerController controller;
    private Model model;

    @Before
    public void setUp() {
        model = new ExtendedModelMap();
    }

    @After
    public void cleanUp() {
        List<Manager> managers = managerDao.list();
        for (Manager manager : managers)
            managerDao.remove(manager);
    }

    @Test
    public void testShowManager() {
        Manager manager = new Manager("Bob Dylan");
        managerDao.add(manager);

        String view = controller.showManager(model);
        assertEquals("manager/list", view);

        List<Manager> listFromDao = managerDao.list();
        Collection<?> listFromModel = (Collection<?>) model.asMap().get("managers");

        assertTrue(listFromModel.contains(manager));
        assertTrue(listFromDao.containsAll(listFromModel));
    }

    @Test
    public void testDeleteManagerOk() throws ManagerDeleteException {
        // prepare ID to delete
        Manager john = new Manager("John Lennon");
        managerDao.add(john);
        long id = john.getId();

        // delete and assert
        String view = controller.deleteManager(id);
        assertEquals("redirect:/managers", view);
        assertNull(managerDao.find(id));
    }

    @Test(expected = ManagerDeleteException.class)
    public void testDeleteManager() throws ManagerDeleteException {
        // prepare ID to delete
        Manager john = new Manager("John Lennon");
        managerDao.add(john);
        long id = john.getId();

        ManagerDao mockedDao = mock(ManagerDao.class);
        when(mockedDao.removeManager(john)).thenReturn(false);

        ManagerDao originalDao = controller.getManagerDao();
        try {
            controller.setManagerDao(mockedDao);
            controller.deleteManager(id);
        } finally {
            controller.setManagerDao(originalDao);
        }
    }

    @Test
    public void testHandleDeleteException() {
        // prepare ID to delete
        Manager john = new Manager("John Lennon");
        ManagerDeleteException e = new ManagerDeleteException(john);
        ModelAndView modelAndView = controller.handDeleteException(e);

        assertEquals("managers/delete-error", modelAndView.getViewName());
        assertTrue(modelAndView.getModelMap().containsValue(john));
    }

    @Test
    public void testGetManager() {
        Manager george = new Manager("George Harrison");
        managerDao.add(george);
        long id = george.getId();

        String view = controller.getManager(id, model);
        assertEquals("managers/view", view);
        assertEquals(george, model.asMap().get("manager"));
    }

    @Test
    public void testUpdateManager() {
        Manager ringo = new Manager("Ringo Starr");
        managerDao.add(ringo);
        long id = ringo.getId();

        ringo.setName("Rango Starr");

        String view = controller.updateManager(id, ringo);
        assertEquals("redirect:/managers", view);
        assertEquals("Rango Starr", managerDao.find(id));
    }

    @Test
    public void testAddManager() {
        Manager paul = new Manager("Paul McCartney");

        String view = controller.addManager(paul);
        assertEquals("redirect:/managers", view);

        assertEquals(paul, managerDao.find(paul.getId()));
    }
}
