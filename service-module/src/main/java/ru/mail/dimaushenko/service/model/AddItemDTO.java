package ru.mail.dimaushenko.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddItemDTO {

    private Long id;
    @NotNull
    @Size(min = 1, max = 40)
    private String name;
    @Size(min = 0, max = 255)
    private String description;
    private ItemDetailsDTO itemDetails;
    private Long shopId;

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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

}
