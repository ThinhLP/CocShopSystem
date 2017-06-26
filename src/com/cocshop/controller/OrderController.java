package com.cocshop.controller;

import com.cocshop.View.view;
import com.cocshop.model.TblOrder;
import com.cocshop.model.TblOrderdetails;
import com.cocshop.model.TblProduct;
import com.cocshop.repository.OrderRepository;
import com.fasterxml.jackson.annotation.JsonView;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Nguyen Cong Chinh on 6/22/2017.
 */
@Controller
public class OrderController {

    @Autowired
    OrderRepository orderRepository;


    @JsonView(view.listOrderDetailsForCustomerId.class)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/customer/orderDetails")
    public List listOrderDetailsFromCusId(int customerId) {
        List<TblOrderdetails> list = orderRepository.listOrderByCusId(customerId);
        return list;
    }

    @JsonView(view.listOrderByDate.class)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/customers/viewOrderByOrderDate")
    public List listOrderByDate(String orderDate) {
        List<TblOrderdetails> list = orderRepository.listOrderByDate(orderDate);
        return list;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/order/checkOut")
    @ResponseBody
    public Boolean checkOut(@RequestBody List<TblProduct> listData) {
        System.err.println("AHHI");
        for (int i = 0; i < listData.size(); i++) {
            System.err.println("ID: " + listData.get(i).getProductId());
        }
        return true;
    }
}