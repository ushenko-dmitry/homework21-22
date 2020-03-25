package ru.mail.dimaushenko.repository.impl;

import org.springframework.stereotype.Repository;
import ru.mail.dimaushenko.repository.ItemRepository;
import ru.mail.dimaushenko.repository.model.Item;

@Repository
public class ItemRepositoryImpl extends GenericRepositoryImpl<Long, Item> implements ItemRepository {

}
