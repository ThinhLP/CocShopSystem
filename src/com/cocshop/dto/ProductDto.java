package com.cocshop.dto;

import com.cocshop.model.TblCategory;
import com.cocshop.model.TblProduct;

/**
 * Created by Nguyen Cong Chinh on 7/11/2017.
 */
public class ProductDto {
    private int productId;
    private String productName;
    private int quantity;
    private double price;
    private String description;
    private CategoryDto category;
    private String imageUrl;

    public ProductDto() {
    }

    public ProductDto(TblProduct product) {
        productId = product.getProductId();
        productName = product.getProductName();
        quantity = product.getQuantity();
        price = product.getPrice();
        description = product.getDescription();
        category = new CategoryDto(product.getTblCategoryByTblCategoryCategoryId());
        imageUrl = product.getImageUrl();
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


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
