package ru.mail.dimaushenko.repository.model;

import java.math.BigDecimal;

public class ItemsFilter {

    private String name;
    private BigDecimal priceMin;
    private BigDecimal priceMax;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(BigDecimal priceMin) {
        this.priceMin = priceMin;
    }

    public BigDecimal getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(BigDecimal priceMax) {
        this.priceMax = priceMax;
    }

}
