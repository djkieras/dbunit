package net.davekieras.dao;

public interface GenericDao<T> {

    public void persist(T entity);
    
    public T findById(long id);
    
    public void clearContext();
    
    public void flush();
    
}
