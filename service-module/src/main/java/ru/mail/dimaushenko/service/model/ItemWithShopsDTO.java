package ru.mail.dimaushenko.service.model;

import java.util.ArrayList;
import java.util.List;

public class ItemWithShopsDTO {

    private Long id;
    private String name;
    private String description;
    private ItemDetailsDTO itemDetails;
    private List<ShopDTO> shops = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemDetailsDTO getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemDetailsDTO itemDetailsDTO) {
        this.itemDetails = itemDetailsDTO;
    }

    public List<ShopDTO> getShops() {
        return shops;
    }

    public void setShops(List<ShopDTO> shops) {
        this.shops = shops;
    }

}
