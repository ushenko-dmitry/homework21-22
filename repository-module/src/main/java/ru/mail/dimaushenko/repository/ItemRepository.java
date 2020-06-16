package ru.mail.dimaushenko.repository;

import java.util.List;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.repository.model.ItemsFilter;
import ru.mail.dimaushenko.repository.model.Pagination;

public interface ItemRepository extends GenericRepository<Long, Item> {

    List<Item> findLimitFilteredBy(Pagination pagination, ItemsFilter filter);
    
}
