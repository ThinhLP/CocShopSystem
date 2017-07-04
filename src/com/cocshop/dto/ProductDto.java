package com.cocshop.dto;

import com.cocshop.model.TblCategory;
import com.cocshop.model.TblProduct;

/**
 * Created by Ken on 7/4/2017.
 */
public class ProductDto {
    private int productId;
    private String productName;
    private int quantity;
    private double price;
    private String createAt;
    private String updateAt;
    private String description;
    private TblCategory category;
    private Boolean deleted;

    public ProductDto() {
    }

    public ProductDto(TblProduct product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
        this.createAt = product.getCreateAt();
        this.updateAt = product.getUpdateAt();
        this.description = product.getDescription();
        this.category = product.getTblCategoryByTblCategoryCategoryId();
        this.deleted = product.getDeleted();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TblCategory getCategory() {
        return category;
    }

    public void setCategory(TblCategory category) {
        this.category = category;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
