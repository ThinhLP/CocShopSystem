package com.cocshop.services;

import com.cocshop.dto.OrderDetailDto;
import com.cocshop.dto.OrderDto;
import com.cocshop.dto.ProductDto;
import com.cocshop.dto.UserDto;
import com.cocshop.model.TblOrder;
import com.cocshop.model.TblOrderdetails;
import com.cocshop.model.TblUser;
import com.cocshop.repository.OrderDetailRepository;
import com.cocshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Nguyen Cong Chinh on 7/11/2017.
 */
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    public List<OrderDto> listAllOrderOfCustomer(int customerId) {
        List<TblOrder> list = orderRepository.listOrderByCustomer(customerId);
        List<OrderDto> listOrderDto = new ArrayList<>();
        for (TblOrder order: list) {
            listOrderDto.add(convertToOrderDto(order));
        }
        return listOrderDto;
    }
    private OrderDto convertToOrderDto(TblOrder order) {
        OrderDto dto = new OrderDto();
        dto.setOrderId(order.getOrderId());
        //dto.setCustomer(new UserDto(order.getTblUserByCustomerId()));
        TblUser customer = order.getTblUserByCustomerId();
        TblUser employee = order.getTblUserByEmployeeId();
        if (customer != null)
            dto.setCustomerId(customer.getUserId());
        if (employee != null)
            dto.setEmployeeId(employee.getUserId());
        dto.setOrderDate(order.getOrderDate());
        List<TblOrderdetails> list = orderDetailRepository.getOrderByOrderId(order.getOrderId());
        if (list == null || list.isEmpty()) {
            dto.setOrderDetails(null);
        } else {
            List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();
            for (TblOrderdetails orderdetails: list) {
                orderDetailDtoList.add(convertToOrderDetailDto(orderdetails));
            }
            dto.setOrderDetails(orderDetailDtoList);
        }
        return dto;
    }

    private OrderDetailDto convertToOrderDetailDto(TblOrderdetails orderdetails) {
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setQuantity(orderdetails.getQuantity());
        orderDetailDto.setProduct(new ProductDto(orderdetails.getTblProductByTblProductProductId()));
        return orderDetailDto;
    }


    public List<OrderDto> listAllOrderByEmployee(int empId) {
        List<TblOrder> list = orderRepository.listOrderByEmployee(empId);
        List<OrderDto> listOrderDto = new ArrayList<>();
        for (TblOrder order: list) {
            listOrderDto.add(convertToOrderDto(order));
        }
        return listOrderDto;
    }
}
