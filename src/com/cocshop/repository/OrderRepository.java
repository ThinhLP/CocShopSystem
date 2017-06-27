package com.cocshop.repository;

import com.cocshop.model.TblOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Nguyen Cong Chinh on 6/27/2017.
 */
public interface OrderRepository extends CrudRepository<TblOrder, Integer> {

    @Query(value = "select p from TblOrder  p where p.orderId = (select max (p.orderId) from TblOrder)")
    public TblOrder getLastRecord();

    @Query(value = "select p from TblOrder  p")
    public List<TblOrder> getAll();
}
