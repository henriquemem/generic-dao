package br.com.generic.dao;

import br.com.generic.dao.exception.NotPossibleCreateEntityException;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author henrique
 */
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    private EntityManager manager;

    @SuppressWarnings("unchecked")
    private final Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];

    public T createModel(){
        try {
            return entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new NotPossibleCreateEntityException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new NotPossibleCreateEntityException(e);
        }
    }

    @Override
    public T insert(T entity) {
        entity = beforeInsert(consist(entity));
        getEntityManager().persist(entity);
        entity = getEntityManager().merge(entity);
        return afterInsert(entity);
    }

    @Override
    public T delete(T entity) {
        entity = beforeDelete(entity);
        entity = getEntityManager().merge(entity);
        getEntityManager().remove(entity);
        return afterDelete(entity);
    }

    @Override
    public T update(T entity) {
        entity = beforeUpdate(consist(entity));
        entity = getEntityManager().merge(entity);
        return afterUpdate(entity);

    }

    @Override
    public T disassociate(T entity) {
        getEntityManager().detach(entity);
        return entity;
    }

    @Override
    public List<T> list(int beginning, int end, String order) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        criteriaQuery.distinct(true);
        if (order != null)
            criteriaQuery.orderBy(builder.asc(root.get(order)));

        TypedQuery<T> query = getEntityManager().createQuery(criteriaQuery);

        if (beginning > 0)
            query.setFirstResult(beginning);
        if (end > 0)
            query.setMaxResults(end);

        return query.getResultList();
    }

    @Override
    public SearchEntityListBuilder<T> listEntities() {
        return new SearchEntityListBuilderImpl<T>(getEntityManager(), entityClass);
    }

    @Override
    public T findEntityById(long id) {
        return (T) getEntityManager().find(entityClass, id);
    }

    @Override
    public SearchEntityBuilder<T> searchEntity() {
        return new SearchEntityBuilderImpl<T>(getEntityManager(), entityClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> SearchListBuilder<T, E> listProperties(String field) {
        ParameterizedType paramType;
        paramType = (ParameterizedType) new Param<E>().getClass().getGenericInterfaces()[0];
        Class<E> parameterClass = (Class<E>) paramType.getActualTypeArguments()[0].getClass();

        SearchListBuilder<T, E> searchListBuilder = new SearchListBuilderImpl<>(getEntityManager(), entityClass,
                parameterClass);
        searchListBuilder.setField(field);
        return searchListBuilder;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> SearchBuilder<T, E> searchProperty(String field) {
        ParameterizedType paramType;
        paramType = (ParameterizedType) new Param<E>().getClass().getGenericInterfaces()[0];
        Class<E> parameterClass = (Class<E>) paramType.getActualTypeArguments()[0].getClass();

        SearchBuilder<T, E> searchBuilder = new SearchBuilderImpl<>(getEntityManager(), entityClass, parameterClass);
        searchBuilder.setField(field);
        return searchBuilder;
    }

    protected T consist(T entity) {
        return entity;
    }

    protected T beforeInsert(T entity) {
        return entity;
    }

    protected T afterInsert(T entity) {
        return entity;
    }

    protected T beforeDelete(T entity) {
        return entity;
    }

    protected T afterDelete(T entity) {
        return entity;
    }

    protected T beforeUpdate(T entity) {
        return entity;
    }

    protected T afterUpdate(T entity) {
        return entity;
    }

    protected EntityManager getEntityManager() {
        return this.manager;
    }

    public void setEntityManager(EntityManager manager) {
        this.manager = manager;
    }
    
    public Class<T> getEntityClass() {
        return entityClass;
    }

    protected class Param<X> implements IPatam<X> {
        public Param() {
        }
    }

    private interface IPatam<X> {

    }
}
