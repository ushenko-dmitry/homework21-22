package ru.mail.dimaushenko.service.model;

public class ItemDTO {

    private Long id;
    private String name;
    private String description;
    private ItemDetailsDTO itemDetails;

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

    @Override
    public String toString() {
        return "ItemDTO{" + "id=" + id + ", name=" + name + ", description=" + description + ", itemDetails=" + itemDetails + '}';
    }

}
