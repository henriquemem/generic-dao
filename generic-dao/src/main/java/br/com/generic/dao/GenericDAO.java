package br.com.generic.dao;

import java.util.List;


public abstract interface GenericDAO<T> {

    public T insert(T entity);
    
    public T delete(T entity);

    public T update(T entity);

    public T disassociate(T entity);

    public T findEntityById(long id);

    public List<T> list(int beginning, int end, String order);

	public WhereEntityListBuild<T> listEntities();

	public WhereEntityBuild<T> searchEntity();

    public <E> WhereListBuild<T, E> listProperties(String field);

    public<E> WhereBuild<T, E> searchProperty(String field);
    
}
