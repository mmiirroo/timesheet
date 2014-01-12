package org.timesheet.web.editors;

import org.timesheet.domain.Manager;
import org.timesheet.service.dao.ManagerDao;

import java.beans.PropertyEditorSupport;

/**
 * User: seiyaa
 * Date: 14-1-10
 * Time: 下午5:29
 * Git: https://github.com/seiyaa
 */
public class ManagerEditor extends PropertyEditorSupport {

    private ManagerDao managerDao;

    public ManagerEditor(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        long id = Long.parseLong(text);
        Manager manager = managerDao.find(id);
        setValue(manager);
    }
}
