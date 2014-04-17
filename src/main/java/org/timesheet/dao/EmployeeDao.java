package org.timesheet.dao;

import org.timesheet.domain.Employee;

public interface EmployeeDao extends GenericDao<Employee, Long> {
    boolean removeEmployee(Employee employee);
}
