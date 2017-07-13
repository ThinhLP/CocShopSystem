package com.cocshop.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ken on 7/13/2017.
 */
public class CartDto implements Serializable {

    private int userId;

    private int employeeId;

    private List<CartItemDto> cartItems;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }
}
