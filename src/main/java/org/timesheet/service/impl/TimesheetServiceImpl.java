package org.timesheet.service.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.timesheet.dao.TaskDao;
import org.timesheet.domain.Employee;
import org.timesheet.domain.Manager;
import org.timesheet.domain.Task;
import org.timesheet.service.TimesheetService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-17
 * Time: 下午11:11
 * To change this template use File | Settings | File Templates.
 */

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
@Service("timesheetService")
public class TimesheetServiceImpl implements TimesheetService {

    private SessionFactory sessionFactory;
    private TaskDao taskDao;
//    private Random randomdom = new Random();

    public TaskDao getTaskDao() {
        return taskDao;
    }

    @Resource
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public SessionFactory getSessionFactory() {

        return sessionFactory;
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Task busiestTask() {
        List<Task> tasks = taskDao.list();
        if (tasks.isEmpty()) {
            return null;
        }

        Task busiest = tasks.get(0);
        for (Task task : tasks) {
            if (task.getAssignedEmployees().size() > busiest.getAssignedEmployees().size())
                busiest = task;
        }
        return busiest;
    }

    @Override
    public List<Task> tasksForEmployee(Employee employee) {
        List<Task> allTasks = taskDao.list();
        List<Task> tasksForEmployee = new ArrayList<Task>();
        for (Task task : allTasks) {
            if (task.getAssignedEmployees().contains(employee))
                tasksForEmployee.add(task);
        }
        return tasksForEmployee;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Task> tasksForManager(Manager manager) {
        Query query = currentSession().createQuery("from Task t where t.manager.id = :id");
        query.setParameter("id", manager.getId());
        return query.list();
    }
}
