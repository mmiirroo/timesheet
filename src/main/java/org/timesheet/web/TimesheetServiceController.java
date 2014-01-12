package org.timesheet.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.timesheet.domain.Employee;
import org.timesheet.domain.Manager;
import org.timesheet.domain.Task;
import org.timesheet.service.TimesheetService;
import org.timesheet.service.dao.EmployeeDao;
import org.timesheet.service.dao.ManagerDao;
import org.timesheet.web.editors.ManagerEditor;
import org.timesheet.web.helpers.EmployeeEditor;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: seiyaa
 * Date: 14-1-11
 * Time: 下午3:44
 * Git: https://github.com/seiyaa
 */
@Controller
@RequestMapping("/timesheet-service")
public class TimesheetServiceController {
    private TimesheetService service;
    private EmployeeDao employeeDao;
    private ManagerDao managerDao;

    @RequestMapping(method = RequestMethod.GET)
    public String showMenu(Model model) {
        model.addAttribute("busiestTask", service.busiestTask());
        model.addAttribute("employees", employeeDao.list());
        model.addAttribute("managers", managerDao.list());

        return "timesheet-service/menu";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Employee.class, new EmployeeEditor(employeeDao));
        binder.registerCustomEditor(Manager.class, new ManagerEditor(managerDao));
    }

    @RequestMapping(value = "/manager-tasks/{id}", method = RequestMethod.GET)
    public String showManagerTasks(@PathVariable("id") long id, Model model) {
        Manager manager = managerDao.find(id);
        List<Task> tasks = service.tasksForManager(manager);
        model.addAttribute("manager", manager);
        model.addAttribute("tasks", tasks);
        return "timesheet-service/manager-tasks";
    }

    @RequestMapping(value = "employee-tasks/{id}", method = RequestMethod.GET)
    public String showEmployeeTasks(@PathVariable("id") long id,
                                    Model model) {
        Employee employee = employeeDao.find(id);
        List<Task> tasks = service.tasksForEmployee(employee);
        model.addAttribute("employee", employee);
        model.addAttribute("tasks", tasks);
        return "timesheet-service/employee-tasks";
    }

    public TimesheetService getService() {
        return service;
    }

    @Resource
    public void setService(TimesheetService service) {
        this.service = service;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    @Resource
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public ManagerDao getManagerDao() {
        return managerDao;
    }

    @Resource
    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }
}
