package br.com.generic.dao;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.generic.dao.type.PrimitiveType;
import br.com.generic.exceptions.BaseRuntimeException;

/**
 *
 * @author henrique
 */
public abstract class GenericDAOImpl<T> implements GenericDAO<T>{
	
	private EntityManager manager;
	
	
	@SuppressWarnings("unchecked")
	private final Class<T> entityClass = (Class<T>) ((ParameterizedType) 
			getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	
	@Override
    public T insert(T entity){
    	entity = beforeInsert( consist(entity));
    	entity = this.manager.merge(entity);
		return afterInsert(entity);
	}
	
	@Override
	public T delete(T entity){
		entity = beforeDelete(entity);
		entity = this.manager.merge(entity);
		this.manager.remove(entity);
		setIdNull(entity);
		return afterDelete(entity);
	}
	
	private void setIdNull(T entity){
		if(!setIdFieldNull(entity)){
			setIdMethodNull(entity);
		}
	}
	
	private boolean setIdFieldNull(T entity){
		boolean r = false;
		
		Field fieldId = null;
		Class<?> entityClass = this.entityClass;
		fieldId = getIdField(entityClass);
		while (fieldId == null && !entityClass.equals(Object.class)){
			entityClass = entityClass.getSuperclass();
			fieldId = getIdField(entityClass);
		}
		
		if(fieldId != null){
			fieldId.setAccessible(true);
			Type t =  fieldId.getType();
			try {
				if(PrimitiveType.isPrimitiveType(t)){
					fieldId.set(entity, PrimitiveType.getPrimitiveType(t).getValueDefalt());
				}else{
					fieldId.set(entity, null);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				throw new BaseRuntimeException(e);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new BaseRuntimeException(e);
			}
			r = true;
		}
		
		return r;
	}
	
	private boolean setIdMethodNull(T entity){
		boolean r = false;
		
		Method methodId = null;
		Class<?> entityClass = this.entityClass;
		methodId = getIdMethod(entityClass);
		while (methodId == null && !entityClass.equals(Object.class)){
			entityClass = entityClass.getSuperclass();
			methodId = getIdMethod(entityClass);
		}
		
		if(methodId != null){
			methodId.setAccessible(true);
			try {
				Object value = null;
				methodId.invoke(entity, value);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new BaseRuntimeException(e);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				throw new BaseRuntimeException(e);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				throw new BaseRuntimeException(e);
			}
			r = true;
		}
		
		return r;
	}
	
	private Field getIdField(Class<?> entityClass){
		Field fieldId = null;
		for(Field field : entityClass.getDeclaredFields()){
			if(field.isAnnotationPresent(Id.class)){
				fieldId = field;
			}
		}
		return fieldId;
	}
	
	private Method getIdMethod(Class<?> entityClass){
		Method methodId = null;
		for(Method method : entityClass.getDeclaredMethods()){
			if(method.isAnnotationPresent(Id.class)){
				methodId = method;
			}
		}
		return methodId;
	}

	@Override
	public T update(T entity){
		entity = beforeUpdate( consist(entity));
		entity = this.manager.merge(entity);
		return afterUpdate(entity);
	
	}
	
	@Override
	public T disassociate(T entity){
		manager.detach(entity);
		return entity;
	}
	
	@Override
	public List<T> list(int beginning, int end, String order){
		CriteriaBuilder builder = manager.getCriteriaBuilder() ;
		CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);
		Root<T> root = criteriaQuery.from(entityClass);
		criteriaQuery.select(root) ;
		criteriaQuery.distinct(true);
		if(order != null)
			criteriaQuery.orderBy(builder.asc(root.get(order)));
		
		TypedQuery<T> query = manager.createQuery(criteriaQuery);
		
		if(beginning > 0)
			query.setFirstResult(beginning);
		if(end > 0)
			query.setMaxResults(end);
		
		
		return query.getResultList();
	}
	
	@Override
	public SearchEntityListBuilder<T> listEntities() {
		return new SearchEntityListBuilder<T>(manager, entityClass);
	}
	
	@Override
	public T findEntityById(long id) {
		return (T) this.manager.find(entityClass, id);
	}
	
	@Override
	public SearchEntityBuilder<T> searchEntity() {
		return new SearchEntityBuilder<T>(manager, entityClass);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <E> SearchListBuilder<T, E> listProperties(String field) {
		ParameterizedType paramType;
        paramType = (ParameterizedType) new Param<E>().getClass().getGenericInterfaces()[0];
        Class<E> parameterClass = (Class<E>) paramType.getActualTypeArguments()[0].getClass();

        SearchListBuilder<T, E> searchListBuilder = new SearchListBuilder<T, E>(manager , entityClass, parameterClass);
		searchListBuilder.setField(field);
		return searchListBuilder;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <E> SearchBuilder<T, E> searchProperty(String field) {
		ParameterizedType paramType;
        paramType = (ParameterizedType) new Param<E>().getClass().getGenericInterfaces()[0];
        Class<E> parameterClass = (Class<E>) paramType.getActualTypeArguments()[0].getClass();
		
        SearchBuilder<T, E> searchBuilder = new SearchBuilder<>(manager, entityClass, parameterClass);
        searchBuilder.setField(field);
        return searchBuilder;
	}
	
	protected T consist(T entity){ 
		return entity;
	}
	
	protected T beforeInsert(T entity){ 
		return entity;
	}
	
	protected T afterInsert(T entity){ 
		return entity;
	}
	
	protected T beforeDelete(T entity){ 
		return entity;
	}
	
	protected T afterDelete(T entity){ 
		return entity;
	}
	
	protected T beforeUpdate(T entity){ 
		return entity;
	}
	
	protected T afterUpdate(T entity){ 
		return entity;
	}
	
	public EntityManager getEntityManager() {
		return manager;
	}

	public void setEntityManager(EntityManager manager) {
		this.manager = manager;
	}
			
	private class Param<X> implements IPatam<X>{
		private Param(){}
	}
	
	private interface IPatam<X>{
		
	}
}

