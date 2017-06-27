package com.cocshop.controller;

import com.cocshop.View.view;
import com.cocshop.model.*;
import com.cocshop.repository.OrderDetailRepository;
import com.cocshop.repository.OrderRepository;
import com.cocshop.repository.ProductRepository;
import com.cocshop.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by Nguyen Cong Chinh on 6/22/2017.
 */
@Controller
public class OrderController {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @JsonView(view.listOrderDetailsForCustomerId.class)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/customer/orderDetails")
    public List listOrderDetailsFromCusId(int customerId) {

        List<TblOrderdetails> list = orderDetailRepository.listOrderByCusId(customerId);
        return list;
    }

    @JsonView(view.listOrderByDate.class)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/customers/viewOrderByOrderDate")
    public List listOrderByDate(String orderDate) {
        List<TblOrderdetails> list = orderDetailRepository.listOrderByDate(orderDate);
        return list;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/api/order/checkOut", consumes = "application/json")
    @ResponseBody
    public Boolean checkOut(@RequestBody String listOrder) throws IOException {
        System.err.println("List Order: " + listOrder);
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(List.class,TblJsonField.class);
        List<TblJsonField>  list = mapper.readValue(listOrder,collectionType);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        TblOrder order = new TblOrder();
        order.setOrderDate(dateFormat.format(date));
        order.setTblUserByCustomerId(userRepository.findOne(list.get(0).getUserId()));
        orderRepository.save(order);
        order = orderRepository.getLastRecord();
        for(int i = 0 ; i < list.size();i++){
            TblOrderdetailsPK pk = new TblOrderdetailsPK();
            TblOrderdetails tblOrderdetails = new TblOrderdetails();
            TblProduct tblProduct = new TblProduct();
            pk.setTbl_Order_OrderId(order.getOrderId());
            pk.setTbl_Product_ProductId(list.get(i).getProductId());
            tblOrderdetails.setQuantity(list.get(i).getQuantity());
            tblOrderdetails.setPrice(list.get(i).getPrice());
            tblOrderdetails.setPk(pk);
            tblProduct = productRepository.findOne(list.get(i).getProductId());
            tblProduct.setQuantity(tblProduct.getQuantity() - list.get(i).getQuantity());
            productRepository.save(tblProduct);
            orderDetailRepository.save(tblOrderdetails);
        }
        return true;

    }
}