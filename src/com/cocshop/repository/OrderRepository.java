package com.cocshop.repository;

import com.cocshop.model.TblOrderdetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Nguyen Cong Chinh on 6/22/2017.
 */
public interface OrderRepository extends CrudRepository<TblOrderdetails, Integer> {

    @Query(value = "Select p from  TblOrderdetails  p where p.tblOrderByTblOrderOrderId.tblUserByTblUserUserId.userId=:userId")
    public List<TblOrderdetails> listOrderByCusId(@Param("userId")int userId);


    @Query(value = "select  p from TblOrderdetails p where p.tblOrderByTblOrderOrderId.orderDate=:orderDate")
    public List<TblOrderdetails> listOrderByDate(@Param("orderDate") String orderDate);
}
