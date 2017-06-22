package com.cocshop.controller;

import com.cocshop.View.view;
import com.cocshop.model.TblOrderdetails;
import com.cocshop.repository.OrderRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

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
    public List listOrderDetailsFromCusId(int  customerId){
        List<TblOrderdetails> list = orderRepository.listOrderByCusId(customerId);
        return list;
    }

    @JsonView(view.listOrderByDate.class)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/customers/viewOrderByOrderDate")
    public List listOrderByDate(String orderDate){
        List<TblOrderdetails> list = orderRepository.listOrderByDate(orderDate);
        return  list;
    }
}
