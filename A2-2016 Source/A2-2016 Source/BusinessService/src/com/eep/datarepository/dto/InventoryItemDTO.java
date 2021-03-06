/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository.dto;

/**
 * Abstract class template for different database tables. Chances are that the
 * schema of the tables may change, therefore, separate DTOs are still needed.
 *
 * @author zhongzhu
 */
public abstract class InventoryItemDTO {

    private String productCode;
    private String description;
    private Integer quantity;
    private Double price;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return productCode + ", " + description + ", " + quantity + ", " + price;
    }

}
