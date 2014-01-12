package org.timesheet.web.exceptions;

import org.timesheet.domain.Employee;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-26
 * Time: 下午11:24
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeDeleteException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Employee employee;

    public EmployeeDeleteException(Employee toDelete) {
        this.employee = toDelete;
    }

    public Employee getEmployee() {
        return employee;
    }
}
