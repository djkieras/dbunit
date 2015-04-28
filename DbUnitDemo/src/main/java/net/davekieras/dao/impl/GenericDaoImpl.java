package net.davekieras.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.davekieras.dao.GenericDao;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {
	
	@PersistenceContext
    private EntityManager em;

    private Class<T> type;

    @SuppressWarnings("unchecked")
	public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class<T>) pt.getActualTypeArguments()[0];
    }

	@Override
	public void persist(T entity) {
		this.em.persist(entity);
	}

	@Override
	public T findById(long id) {
		return (T) this.em.find(type, id);
	}
	
	protected EntityManager getEntityManager() {
		return this.em;
	}
	
	@Override
	public void clearContext() {
		this.em.clear();
	}
	
	@Override
	public void flush() {
		this.em.flush();
	}
	
}
