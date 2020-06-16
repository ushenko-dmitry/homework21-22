package ru.mail.dimaushenko.service.model;

import java.util.List;

public class ViewItemsDTO {

    private PaginationDTO pagination;
    private ItemsFilterDTO filter;
    private List<ItemDTO> items;

    public PaginationDTO getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }

    public ItemsFilterDTO getFilter() {
        return filter;
    }

    public void setFilter(ItemsFilterDTO filter) {
        this.filter = filter;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ViewItemsDTO{" + "pagination=" + pagination + ", filter=" + filter + ", items=" + items + '}';
    }

}
