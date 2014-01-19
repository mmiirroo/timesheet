package org.timesheet.web.editors;

import org.timesheet.domain.Employee;
import org.timesheet.service.dao.EmployeeDao;

import java.beans.PropertyEditorSupport;

/**
 * User: seiyaa
 * Date: 14-1-11
 * Time: 下午3:05
 * Git: https://github.com/seiyaa
 */
public class EmployeeEditor extends PropertyEditorSupport {
    private EmployeeDao employeeDao;

    public EmployeeEditor(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        long id = Long.parseLong(text);
        Employee employee = employeeDao.find(id);
        setValue(employee);
    }
}
