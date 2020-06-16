package ru.mail.dimaushenko.repository.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.mail.dimaushenko.repository.ShopRepository;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.repository.model.Shop;
import ru.mail.dimaushenko.repository.model.ShopsFilter;

@Repository
public class ShopRepositoryImpl extends GenericRepositoryImpl<Long, Shop> implements ShopRepository {

    @Override
    public List<Shop> getShopsWithItem(Item item) {
        String queryString = "FROM Shop s "
                + "JOIN s.items i "
                + "WHERE i.id=:itemId";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("itemId", item.getId());
        List resultList = query.getResultList();
        List<Shop> shops = new ArrayList<>();
        for (Object object : resultList) {
            Object[] result = ((Object[]) object);
            Shop shop = (Shop) result[0];
            shops.add(shop);
        }
        return shops;
    }

    @Override
    public List<Shop> findLimitFilteredBy(Pagination pagination, ShopsFilter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shop> criteriaQuery = criteriaBuilder.createQuery(Shop.class);
        Root<Shop> root = criteriaQuery.from(Shop.class);
        if (!filter.getLocation().equals("")) {
            String location = "%" + filter.getLocation() + "%";
            criteriaQuery.where(criteriaBuilder.like(root.get("location"), location));
        }
        criteriaQuery.select(root);
        TypedQuery<Shop> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pagination.getStartElement());
        typedQuery.setMaxResults(pagination.getElementsPerPage());
        return typedQuery.getResultList();
    }

}
