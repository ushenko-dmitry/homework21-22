package ru.mail.dimaushenko.repository;

import java.util.List;
import ru.mail.dimaushenko.repository.model.Pagination;

public interface GenericRepository<I, T> {

    void persist(T entity);

    void merge(T entity);

    void remove(T entity);

    T findById(I id);

    List<T> findAll();

    List<T> findLimit(Pagination pagination);

    Integer getAmountElements();
}
