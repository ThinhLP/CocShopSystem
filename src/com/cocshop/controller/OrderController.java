package com.cocshop.controller;

import com.cocshop.View.view;
import com.cocshop.model.TblJsonField;
import com.cocshop.model.TblOrderdetails;
import com.cocshop.model.TblProduct;
import com.cocshop.model.TblWrapper;
import com.cocshop.repository.OrderRepository;
import com.fasterxml.jackson.annotation.JsonView;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        System.err.println("Ahihi");
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

//    @RequestMapping(method = RequestMethod.POST, value = "/api/order/checkOut")
//    @ResponseBody
//    public Boolean checkOut(@RequestBody List<TblProduct> listData) {
//        System.err.println("AHHI");
//        for (int i = 0; i < listData.size(); i++) {
//            System.err.println("ID: " + listData.get(i).getProductId());
//        }
//        return true;
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/order/checkOut", consumes = "application/json")
    @ResponseBody
    public Boolean checkOut(@RequestBody TblWrapper wrapper){
        List<TblWrapper> list = wrapper.getList();
        for (int i = 0; i < list.size(); i++) {
            System.err.println("ID: " + list.get(i).toString());

        }
        return true;

    }
}