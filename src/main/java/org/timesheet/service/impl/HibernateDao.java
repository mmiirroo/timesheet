package org.timesheet.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.timesheet.service.GenericDao;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class HibernateDao<E, K extends Serializable> implements GenericDao<E, K> {
    private SessionFactory sessionFactory;
    protected Class<? extends E> daoType;

    @SuppressWarnings("unchecked")
    public HibernateDao() {
        this.daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(E entity) {
        currentSession().save(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E find(K key) {
        return (E) currentSession().get(daoType, key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> list() {
        return currentSession().createCriteria(daoType).list();
    }

    @Override
    public void remove(E entity) {
        currentSession().delete(entity);
    }

    @Override
    public void update(E entity) {
        currentSession().update(entity);
    }

}
