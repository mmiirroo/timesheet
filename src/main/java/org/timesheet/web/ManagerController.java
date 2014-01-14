package org.timesheet.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.timesheet.domain.Manager;
import org.timesheet.service.dao.ManagerDao;
import org.timesheet.web.exceptions.ManagerDeleteException;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-12-29 Time: 下午7:41
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/managers")
public class ManagerController {

    private ManagerDao managerDao;

    public ManagerDao getManagerDao() {
        return managerDao;
    }

    @Resource
    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showManager(Model model) {
        List<Manager> managers = managerDao.list();
        model.addAttribute("managers", managers);
        return "managers/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteManager(@PathVariable("id") long id) throws ManagerDeleteException {
        Manager toDelete = managerDao.find(id);
        boolean wasDeleted = managerDao.removeManager(toDelete);

        if (!wasDeleted) {
            throw new ManagerDeleteException(toDelete);
        }
        return "redirect:/managers";
    }

    @ExceptionHandler(ManagerDeleteException.class)
    public ModelAndView handDeleteException(ManagerDeleteException e) {
        ModelMap model = new ModelMap();
        model.put("manager", e.getMessage());
        return new ModelAndView("manager/delete-error", model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getManager(@PathVariable("id") long id, Model model) {
        Manager manager = managerDao.find(id);
        model.addAttribute("manager", manager);
        return "managers/view";
    }

    /**
     * Updates manager with specified ID
     *
     * @param id      Manager's ID
     * @param manager Manager to update (bounded from HTML form)
     * @return redirects to managers
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String updateManager(@PathVariable("id") long id, Manager manager) {
        manager.setId(id);
        managerDao.update(manager);

        return "redirect:/managers";
    }

    /**
     * Saves new manager to the database
     *
     * @param manager Manager to save
     * @return redirects to managers
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addManager(Manager manager) {
        managerDao.add(manager);

        return "redirect:/managers";
    }
    
    @RequestMapping(params = "new", method = RequestMethod.GET)
    public String createManagerForm(Model model) {
        model.addAttribute("manager", new Manager());
        return "managers/new";
    }
}
