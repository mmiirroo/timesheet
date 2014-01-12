package org.timesheet.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.timesheet.domain.Employee;
import org.timesheet.service.dao.EmployeeDao;
import org.timesheet.web.exceptions.EmployeeDeleteException;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-25
 * Time: 下午11:02
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Resource
    private EmployeeDao employeeDao;

    @RequestMapping(method = RequestMethod.POST)
    public String addEmployee(Employee employee) {
        employeeDao.add(employee);

        return "redirect:/employees";
    }

    @RequestMapping(params = "new", method = RequestMethod.GET)
    public String createEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/new";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") long id) throws EmployeeDeleteException {
        Employee toDelete = employeeDao.find(id);
        boolean wasDeleted = employeeDao.removeEmployee(toDelete);

        if (!wasDeleted) {
            throw new EmployeeDeleteException(toDelete);
        }
        return "redirect:/employees";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEmployee(@PathVariable("id") long id, Model model) {
        Employee employee = employeeDao.find(id);
        model.addAttribute("employee", employee);

        return "employees/view";
    }

    @ExceptionHandler(EmployeeDeleteException.class)
    public ModelAndView handleDeleteException(EmployeeDeleteException e) {
        ModelMap model = new ModelMap();
        model.put("employee", e.getEmployee());
        return new ModelAndView("employees/delete-error", model);
    }

    @Resource
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showEmployee(Model model) {
        List<Employee> employeeList = employeeDao.list();
        model.addAttribute("employees", employeeList);
        return "employees/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String updateEmployee(@PathVariable("id") long id, Employee employee) {
        employee.setId(id);
        employeeDao.update(employee);

        return "redirect:/employees";
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }
}
