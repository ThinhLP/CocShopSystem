package com.cocshop.dto;

import com.cocshop.model.TblUser;

import java.util.List;

/**
 * Created by Nguyen Cong Chinh on 7/11/2017.
 */
public class OrderDto {
    private int orderId;
    private String orderDate;
    private UserDto employee;
    private UserDto customer;
    private List<OrderDetailDto> orderDetails;

    public OrderDto() {
    }

    public OrderDto(int orderId, String orderDate, UserDto employee, UserDto customer) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.employee = employee;
        this.customer = customer;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public UserDto getEmployee() {
        return employee;
    }

    public void setEmployee(UserDto employee) {
        this.employee = employee;
    }

    public UserDto getCustomer() {
        return customer;
    }

    public void setCustomer(UserDto customer) {
        this.customer = customer;
    }

    public List<OrderDetailDto> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDto> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
