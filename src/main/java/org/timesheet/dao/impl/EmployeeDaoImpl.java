package org.timesheet.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.timesheet.dao.EmployeeDao;
import org.timesheet.dao.HibernateDao;
import org.timesheet.domain.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends HibernateDao<Employee, Long> implements EmployeeDao {
    @Override
    public boolean removeEmployee(Employee employee) {
        Query employeeTaskQuery = currentSession().createQuery("from Task t where :id in elements(t.assignedEmployees)");
        employeeTaskQuery.setParameter("id", employee.getId());

        if (!employeeTaskQuery.list().isEmpty())
            return false;
        remove(employee);
        return true;
    }
}
