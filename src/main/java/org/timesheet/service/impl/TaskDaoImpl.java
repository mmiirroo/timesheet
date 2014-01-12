package org.timesheet.service.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.timesheet.domain.Task;
import org.timesheet.service.dao.TaskDao;

import java.util.List;

@Repository("taskDao")
public class TaskDaoImpl extends HibernateDao<Task, Long> implements TaskDao {

    @Override
    public boolean removeTask(Task task) {
        Query taskQuery = currentSession().createQuery("from Timesheet t where t.task.id = :id");
        taskQuery.setParameter("id", task.getId());
        if (!taskQuery.list().isEmpty())
            return false;
        remove(task);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Task> list() {
        return currentSession().createCriteria(Task.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }


}
