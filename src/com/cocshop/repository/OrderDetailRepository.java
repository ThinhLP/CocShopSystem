package com.cocshop.repository;

import com.cocshop.model.TblOrder;
import com.cocshop.model.TblOrderdetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Nguyen Cong Chinh on 6/22/2017.
 */
public interface OrderDetailRepository extends CrudRepository<TblOrderdetails, Integer> {


    @Query(value = "select p from TblOrderdetails  p where p.tblOrderByTblOrderOrderId.tblUserByCustomerId.userId=:userId")
    public List<TblOrderdetails> listOrderByCusId(@Param("userId") int userId);


    @Query(value = "select  p from TblOrderdetails p where p.tblOrderByTblOrderOrderId.orderDate=:orderDate")
    public List<TblOrderdetails> listOrderByDate(@Param("orderDate") String orderDate);

    @Query(value = "select p from TblOrderdetails p where p.tblOrderByTblOrderOrderId.orderId=:orderId")
    public List<TblOrderdetails>getOrderByOrderId(@Param("orderId") int orderId);


}
