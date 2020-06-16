package ru.mail.dimaushenko.repository.impl;

import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.mail.dimaushenko.repository.UserRepository;
import ru.mail.dimaushenko.repository.model.User;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<Long, User> implements UserRepository {

    @Override
    public User getEntityByUsername(String username) {
        String queryString = "FROM " + entityClass.getSimpleName() + " u "
                + "WHERE u.username=:username";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }

    @Override
    public boolean isUsernameFound(String username) {
        String queryString = "SELECT count(id) FROM " + entityClass.getName() + " u WHERE u.username=:username";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("username", username);
        Long count = (Long) query.getSingleResult();
        return count.intValue() > 0;
    }

}
