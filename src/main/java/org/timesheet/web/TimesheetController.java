package org.timesheet.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.timesheet.domain.Timesheet;
import org.timesheet.service.dao.EmployeeDao;
import org.timesheet.service.dao.TaskDao;
import org.timesheet.service.dao.TimesheetDao;
import org.timesheet.web.commands.TimesheetCommand;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * User: seiyaa
 * Date: 14-1-11
 * Time: 上午11:26
 * Git: https://github.com/seiyaa
 */
@Controller
@RequestMapping("/timesheets")
public class TimesheetController {
    private TimesheetDao timesheetDao;
    private TaskDao taskDao;
    private EmployeeDao employeeDao;

    @RequestMapping(method = RequestMethod.GET)
    public String showTimesheets(Model model) {
        List<Timesheet> timesheets = timesheetDao.list();
        model.addAttribute("timesheets", timesheets);
        return "timesheets/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteTimesheet(@PathVariable("id") long id) {
        Timesheet toDelete = timesheetDao.find(id);
        timesheetDao.remove(toDelete);
        return "redirect:/timesheets";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getTimesheet(@PathVariable("id") long id, Model model) {
        Timesheet timesheet = timesheetDao.find(id);
        TimesheetCommand tsCommand = new TimesheetCommand(timesheet);
        model.addAttribute("tsCommand", tsCommand);
        return "timesheet/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String updateTimesheet(@PathVariable("id") long id,
                                  @Valid @ModelAttribute("tsCommand") TimesheetCommand tsCommand,
                                  BindingResult result) {
        Timesheet timesheet = timesheetDao.find(id);
        if (result.hasErrors()) {
            tsCommand.setTimesheet(timesheet);
            return "timesheets/view";
        }

        timesheet.setHours(tsCommand.getHours());
        timesheetDao.update(timesheet);
        return "redirect:/timesheets";
    }

    @RequestMapping(params = "new", method = RequestMethod.GET)
    public String createTimesheetForm(Model model) {
        model.addAttribute("timesheet", new Timesheet());
        model.addAttribute("tasks", taskDao.list());
        model.addAttribute("employees", employeeDao.list());

        return "timesheets/new";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addTimesheet(Timesheet timesheet) {
        timesheetDao.add(timesheet);
        return "redirect:/timesheets";
    }

    public TimesheetDao getTimesheetDao() {
        return timesheetDao;
    }

    @Resource
    public void setTimesheetDao(TimesheetDao timesheetDao) {
        this.timesheetDao = timesheetDao;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }

    @Resource
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    @Resource
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
