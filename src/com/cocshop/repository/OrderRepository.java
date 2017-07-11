package com.cocshop.repository;

import com.cocshop.model.TblOrder;
import com.cocshop.model.TblOrderdetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Nguyen Cong Chinh on 6/27/2017.
 */
public interface OrderRepository extends CrudRepository<TblOrder, Integer> {

    @Query(value = "select p from TblOrder  p where p.orderId = (select max (p.orderId) from TblOrder)")
    public TblOrder getLastRecord();

    @Query(value = "select p from TblOrder  p")
    public List<TblOrder> getAll();

//    @Query(value = "select p from TblOrderdetails  p where p.tblOrderByTblOrderOrderId.tblUserByCustomerId.userId=:userId")
//    public List<TblOrderdetails> listOrderByCusId(@Param("userId") int userId);
    @Query(value ="select p from TblOrder p, TblOrderdetails a where p.tblUserByCustomerId.userId=:userId and p.orderId=a.tblOrderByTblOrderOrderId.orderId")
    public List<TblOrder> listOrderByCustomerId(@Param("userId") int userId);

    // Edit by ThinhLP
    @Query(value ="select o from TblOrder o where o.tblUserByCustomerId.userId=:userId AND o.tblUserByEmployeeId is not null")
    public List<TblOrder> listOrderByCustomer(@Param("userId") int userId);
}
