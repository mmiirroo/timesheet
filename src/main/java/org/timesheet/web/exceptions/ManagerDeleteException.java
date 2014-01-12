package org.timesheet.web.exceptions;

import org.timesheet.domain.Manager;

public class ManagerDeleteException extends Exception {
    private static final long serialVersionUID = 1L;
    private Manager manager;

    public ManagerDeleteException(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

}
