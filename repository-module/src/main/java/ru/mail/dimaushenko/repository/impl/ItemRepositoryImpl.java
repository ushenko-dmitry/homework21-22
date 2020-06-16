package ru.mail.dimaushenko.repository.impl;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.mail.dimaushenko.repository.ItemRepository;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.repository.model.ItemsFilter;
import ru.mail.dimaushenko.repository.model.Pagination;

@Repository
public class ItemRepositoryImpl extends GenericRepositoryImpl<Long, Item> implements ItemRepository {

    @Override
    public List<Item> findLimitFilteredBy(Pagination pagination, ItemsFilter filter) {
        String queryString = "from Item i "
                + "WHERE i.name like '%" + filter.getName() + "%' "
                + "AND id.itemDetails.price >= " + filter.getPriceMin() + " ";
        if (filter.getPriceMax().compareTo(BigDecimal.ZERO) != 0) {
            queryString += "AND id.itemDetails.price <= " + filter.getPriceMax() + " ";
        }
        Query query = entityManager.createQuery(queryString);
        query.setFirstResult(pagination.getStartElement());
        query.setMaxResults(pagination.getElementsPerPage());
        List resultList = query.getResultList();
        return resultList;
    }

}
