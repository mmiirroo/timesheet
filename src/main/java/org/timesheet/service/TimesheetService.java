package org.timesheet.service;

import org.timesheet.domain.Employee;
import org.timesheet.domain.Manager;
import org.timesheet.domain.Task;

import java.util.List;

public interface TimesheetService {
    Task busiestTask();

    List<Task> tasksForEmployee(Employee e);

    List<Task> tasksForManager(Manager m);
}
