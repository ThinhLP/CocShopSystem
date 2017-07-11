package com.cocshop.dto;

import com.cocshop.model.TblProduct;

/**
 * Created by Nguyen Cong Chinh on 7/11/2017.
 */
public class OrderDetailDto {
    private int quantity;
    private ProductDto product;

    public OrderDetailDto() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }
}
