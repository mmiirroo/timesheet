package org.timesheet.dao;

import org.timesheet.domain.Manager;

public interface ManagerDao extends GenericDao<Manager, Long> {
    boolean removeManager(Manager manager);
}
