package ru.mail.dimaushenko.repository.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ru.mail.dimaushenko.repository.GenericRepository;
import ru.mail.dimaushenko.repository.model.Pagination;

public abstract class GenericRepositoryImpl<I, T> implements GenericRepository<I, T> {

    protected Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public GenericRepositoryImpl() {
        ParameterizedType generalSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) generalSuperclass.getActualTypeArguments()[1];
    }

    @Override
    public void persist(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void merge(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void remove(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public T findById(I id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        String queryString = "from " + entityClass.getName() + " c";
        Query query = entityManager.createQuery(queryString);
        return query.getResultList();
    }

    @Override
    public List<T> findLimit(Pagination pagination) {
        String queryString = "from " + entityClass.getName() + " c";
        Query query = entityManager.createQuery(queryString);
        query.setFirstResult(pagination.getStartElement());
        query.setMaxResults(pagination.getElementsPerPage());
        List resultList = query.getResultList();
        return resultList;
    }

    @Override
    public Integer getAmountElements(){
        String queryString = "SELECT count(id) FROM " + entityClass.getName() + " c";
        Query query = entityManager.createQuery(queryString);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    
}
