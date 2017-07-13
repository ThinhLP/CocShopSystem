package com.cocshop.dto;
import java.io.Serializable;

/**
 * Created by Nguyen Cong Chinh on 6/26/2017.
 */


public class CartItemDto implements Serializable {

    private Integer productId;

    private int quantity;

    private double price;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

}
