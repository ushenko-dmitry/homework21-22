package ru.mail.dimaushenko.repository;

import java.util.List;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.repository.model.Shop;
import ru.mail.dimaushenko.repository.model.ShopsFilter;

public interface ShopRepository extends GenericRepository<Long, Shop> {
    
    List<Shop> getShopsWithItem(Item item);

    List<Shop> findLimitFilteredBy(Pagination pagination, ShopsFilter filter);
}
