package com.cocshop.dto;

import com.cocshop.model.TblUser;

import java.util.List;

/**
 * Created by Nguyen Cong Chinh on 7/11/2017.
 */
public class OrderDto {
    private int orderId;
    private String orderDate;
    private int employeeId;
    private int customerId;
    private List<OrderDetailDto> orderDetails;

    public OrderDto() {
    }

    public OrderDto(int orderId, String orderDate, int employee, int customer) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.employeeId = employee;
        this.customerId = customer;
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


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<OrderDetailDto> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDto> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
