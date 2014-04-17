package org.timesheet.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.timesheet.dao.HibernateDao;
import org.timesheet.dao.TimesheetDao;
import org.timesheet.domain.Timesheet;

import java.util.List;

@Repository("timesheetDao")
public class TimesheetDaoImpl extends HibernateDao<Timesheet, Long> implements TimesheetDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Timesheet> list() {
        return currentSession().createCriteria(Timesheet.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

}
