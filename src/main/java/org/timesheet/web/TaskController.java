package org.timesheet.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.timesheet.domain.Employee;
import org.timesheet.domain.Manager;
import org.timesheet.domain.Task;
import org.timesheet.service.dao.EmployeeDao;
import org.timesheet.service.dao.ManagerDao;
import org.timesheet.service.dao.TaskDao;
import org.timesheet.web.editors.ManagerEditor;
import org.timesheet.web.exceptions.TaskDeleteException;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-4
 * Time: 下午4:18
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/tasks")
public class TaskController {
    private TaskDao taskDao;
    private EmployeeDao employeeDao;
    private ManagerDao managerDao;

    @RequestMapping(method = RequestMethod.GET)
    public String showTasks(Model model) {
        model.addAttribute("tasks", taskDao.list());
        return "tasks/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteTask(@PathVariable("id") long id) throws TaskDeleteException {
        Task toDelete = taskDao.find(id);
        boolean wasDeleted = taskDao.removeTask(toDelete);

        if (!wasDeleted)
            throw new TaskDeleteException(toDelete);

        return "redirect:/tasks";
    }

    @ExceptionHandler(TaskDeleteException.class)
    public ModelAndView handleDeleteException(TaskDeleteException e) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("task", e.getTask());
        return new ModelAndView("tasks/delete-error", modelMap);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getTask(@PathVariable("id") long id, Model model) {
        Task task = taskDao.find(id);
        model.addAttribute("task", task);

        List<Employee> employeeList = employeeDao.list();
        Set<Employee> unassignedEmployees = new HashSet<Employee>();

        for (Employee employee : employeeList) {
            if (!task.getAssignedEmployees().contains(employee)) {
                unassignedEmployees.add(employee);
            }
        }

        model.addAttribute("unassigned", unassignedEmployees);
        return "tasks/view";
    }

    @RequestMapping(value = "/{id}/employees/{employeeId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployee(
            @PathVariable("id") long taskId,
            @PathVariable("employeeId") long employeeId) {
        Employee employee = employeeDao.find(employeeId);
        Task task = taskDao.find(taskId);

        task.removeEmployee(employee);
        taskDao.update(task);
    }

    @RequestMapping(value = "/{id}/employees/{employeeId}", method = RequestMethod.PUT)
    public String addEmployee(@PathVariable("id") long taskId,
                              @PathVariable("employeeId") long employeeId) {
        Employee employee = employeeDao.find(employeeId);
        Task task = taskDao.find(taskId);

        task.addEmployee(employee);
        taskDao.update(task);

        return "redirect:/tasks/" + taskId;
    }

    @RequestMapping(params = "new", method = RequestMethod.GET)
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
        List<Manager> managers = managerDao.list();
        model.addAttribute("managers", managers);

        return "tasks/new";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Manager.class, new ManagerEditor(managerDao));
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addTask(Task task) {
        List<Employee> employees = reduce(employeeDao.list());

        task.setAssignedEmployees(employees);
        taskDao.add(task);
        return "redirect:/tasks";
    }

    private List<Employee> reduce(List<Employee> employees) {
        List<Employee> reduced = new ArrayList<Employee>();
        Random random = new Random();
        int amount = random.nextInt(employees.size()) + 1;

        amount = amount > 5 ? 5 : amount;
        for (int i = 0; i < amount; i++) {
            int randomIdx = random.nextInt(employees.size());
            Employee employee = employees.get(randomIdx);
            reduced.add(employee);
            employees.remove(employee);
        }

        return reduced;
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

    public ManagerDao getManagerDao() {
        return managerDao;
    }

    @Resource
    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }
}
