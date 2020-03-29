package ru.mail.dimaushenko.service.model;

import java.util.List;

public class ViewShopsDTO {

    private PaginationDTO pagination;
    private ShopsFilterDTO filter;
    private List<ShopDTO> shops;

    public PaginationDTO getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }

    public ShopsFilterDTO getFilter() {
        return filter;
    }

    public void setFilter(ShopsFilterDTO filter) {
        this.filter = filter;
    }

    public List<ShopDTO> getShops() {
        return shops;
    }

    public void setShops(List<ShopDTO> shops) {
        this.shops = shops;
    }

}
