package com.cocshop.controller;

import com.cocshop.View.view;
import com.cocshop.dto.CartDto;
import com.cocshop.dto.CartItemDto;
import com.cocshop.dto.OrderDto;
import com.cocshop.model.*;
import com.cocshop.repository.OrderDetailRepository;
import com.cocshop.repository.OrderRepository;
import com.cocshop.repository.ProductRepository;
import com.cocshop.repository.UserRepository;
import com.cocshop.services.OrderService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    OrderService orderService;

    @JsonView(view.listOrderDetailsForCustomerId.class)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/customer/orderDetails")
    public ResponseEntity<List<TblOrderdetails>> listOrderDetailsFromCusId(int customerId) {
        List<TblOrderdetails> list = orderDetailRepository.listOrderByCusId(customerId);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<TblOrderdetails>>(list, HttpStatus.OK);
    }




    @JsonView(view.listOrderByDate.class)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/customers/viewOrderByOrderDate")
    public ResponseEntity<List<TblOrderdetails>> listOrderByDate(String orderDate) {
        List<TblOrderdetails> list = orderDetailRepository.listOrderByDate(orderDate);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<TblOrderdetails>>(list, HttpStatus.OK);
    }


    @JsonView(view.getOrderByOrderId.class)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/order/checkOutDetail")
    public List listDetailsOrder(int orderId) {
        List<TblOrderdetails> list = orderDetailRepository.getOrderByOrderId(orderId);
        return list;
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/order/acceptedOrder", produces = MediaType.TEXT_PLAIN_VALUE)
    public String acceptedOrder(int orderId, int userId) {
        JsonObject jsonObject = new JsonObject();
        TblOrder tblOrder = orderRepository.findOne(orderId);
        tblOrder.setTblUserByEmployeeId(userRepository.findOne(userId));
        orderRepository.save(tblOrder);
        return Boolean.TRUE.toString();
    }


    /** For Mobile API **/
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/1.0/orders")
    public ResponseEntity<List<OrderDto>> listOrderDetailsByCustomer(int customerId) {
        List<OrderDto> orderDtoList = orderService.listAllOrderOfCustomer(customerId);
        return new ResponseEntity(orderDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/1.0/checkout", consumes = "application/json")
    @ResponseBody
    public ResponseEntity checkout(@RequestBody CartDto cart){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        TblOrder order = new TblOrder();
        order.setOrderDate(dateFormat.format(date));
        order.setTblUserByCustomerId(userRepository.findOne(cart.getUserId()));
        order.setTblUserByEmployeeId(userRepository.findOne(cart.getEmployeeId()));
        orderRepository.save(order);
        order = orderRepository.getLastRecord();

        List<TblProduct> productList = new ArrayList<>();
        List<TblOrderdetails> orderdetailsList = new ArrayList<>();
        List<CartItemDto> list = cart.getCartItems();

        for (CartItemDto item: list) {
            // Check quantity
            TblProduct tblProduct = productRepository.findOne(item.getProductId());
            if (item.getQuantity() > tblProduct.getQuantity()) {
                // Rollback
                orderRepository.delete(order.getOrderId());
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            TblOrderdetailsPK pk = new TblOrderdetailsPK();
            TblOrderdetails tblOrderdetails = new TblOrderdetails();
            pk.setTbl_Order_OrderId(order.getOrderId());
            pk.setTbl_Product_ProductId(item.getProductId());
            tblOrderdetails.setQuantity(item.getQuantity());
            tblOrderdetails.setPrice(item.getPrice());
            tblOrderdetails.setPk(pk);
            tblProduct.setQuantity(tblProduct.getQuantity() - item.getQuantity());
            productList.add(tblProduct);
            orderdetailsList.add(tblOrderdetails);
        }
        // Insert to database
        for (int i = 0; i < list.size(); i++) {
            productRepository.save(productList.get(i));
            orderDetailRepository.save(orderdetailsList.get(i));
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}