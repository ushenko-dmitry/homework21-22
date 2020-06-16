package ru.mail.dimaushenko.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddItemDTO {

    @NotNull
    @Size(min = 1, max = 40)
    private String name;
    @Size(min = 0, max = 255)
    private String description;
    private String price;
    private Long[] shopId;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long[] getShopId() {
        return shopId;
    }

    public void setShopId(Long[] shopId) {
        this.shopId = shopId;
    }

}
