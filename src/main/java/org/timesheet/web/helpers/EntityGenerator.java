package org.timesheet.web.helpers;

import org.springframework.stereotype.Service;
import org.timesheet.dao.EmployeeDao;
import org.timesheet.dao.GenericDao;
import org.timesheet.dao.ManagerDao;
import org.timesheet.dao.TaskDao;
import org.timesheet.dao.TimesheetDao;
import org.timesheet.domain.Employee;
import org.timesheet.domain.Manager;

import javax.annotation.Resource;
import java.util.List;

@Service
public final class EntityGenerator {
    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private ManagerDao managerDao;
    @Resource
    private TaskDao taskDao;
    @Resource
    private TimesheetDao timesheetDao;

    public void generateDomain() {
        Employee steve = new Employee("Steve", "Design");
        Employee bill = new Employee("Bill", "Marketing");
        Employee linus = new Employee("linus", "Programming");

        // free employees
        Employee john = new Employee("John", "Beatles");
        Employee george = new Employee("Geoge", "Beatles");
        Employee ringo = new Employee("Ringo", "Beatles");
        Employee paul = new Employee("Paul", "Beatles");

        Manager eric = new Manager("Eric");
        Manager larry = new Manager("Larry");

        // free managers
        Manager simon = new Manager("Simon");
        Manager garfunkel = new Manager("Garfunkel");

        addAll(employeeDao, steve, bill, linus, john, george, ringo, paul);
        addAll(managerDao, eric, larry, simon, garfunkel);
    }

    public void deleteDomain() {
        deleteDomain(timesheetDao);
        deleteDomain(taskDao);
        deleteDomain(managerDao);
        deleteDomain(employeeDao);
    }

    public <T> void deleteDomain(GenericDao<T, Long> dao) {
        List<T> entityList = dao.list();
        for (T entity : entityList) {
            dao.remove(entity);
        }
    }

    private <T> void addAll(GenericDao<T, Long> dao, T... entities) {
        for (T o : entities) {
            dao.add(o);
        }
    }

}
