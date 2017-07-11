package com.cocshop.model;

import com.cocshop.View.view;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Nguyen Cong Chinh on 6/19/2017.
 */
@Entity
@Table(name = "tbl_order", schema = "cocshop", catalog = "")
public class TblOrder {
    @JsonView({view.listOrderDetailsForCustomerId.class,
            view.listOrderByDate.class,view.viewAllOrder.class,view.getOrderByOrderId.class})
    private int orderId;
    @JsonView({view.listOrderDetailsForCustomerId.class,
            view.listOrderByDate.class,view.viewAllOrder.class,view.getOrderByOrderId.class})
    private String orderDate;
    private Collection<TblOrderdetails> tblOrderdetailssByOrderId;
    @JsonView({view.listOrderDetailsForCustomerId.class,
            view.listOrderByDate.class,view.viewAllOrder.class,view.getOrderByOrderId.class})
    //private TblUser tblUserByEmployeeId;
    private TblUser employee;
    @JsonView({view.listOrderDetailsForCustomerId.class,
            view.listOrderByDate.class,view.viewAllOrder.class,view.getOrderByOrderId.class})
    private TblUser customer;
    //private TblUser tblUserByCustomerId;



    @Id
    @Column(name = "orderID")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "orderDate")
    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblOrder tblOrder = (TblOrder) o;

        if (orderId != tblOrder.orderId) return false;
        if (orderDate != null ? !orderDate.equals(tblOrder.orderDate) : tblOrder.orderDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        return result;
    }

//    @ManyToOne
//    @JoinColumn(name = "tbl_user_userID", referencedColumnName = "userID", nullable = false)
//    public TblUser getTblUserByTblUserUserId() {
//        return tblUserByTblUserUserId;
//    }
//
//    public void setTblUserByTblUserUserId(TblUser tblUserByTblUserUserId) {
//        this.tblUserByTblUserUserId = tblUserByTblUserUserId;
//    }


    @OneToMany(mappedBy = "tblOrderByTblOrderOrderId")
    public Collection<TblOrderdetails> getTblOrderdetailssByOrderId() {
        return tblOrderdetailssByOrderId;
    }

    public void setTblOrderdetailssByOrderId(Collection<TblOrderdetails> tblOrderdetailssByOrderId) {
        this.tblOrderdetailssByOrderId = tblOrderdetailssByOrderId;
    }

//    @ManyToOne
//    @JoinColumn(name = "employeeID", referencedColumnName = "userID")
//    public TblUser getTblUserByEmployeeId() {
//        return tblUserByEmployeeId;
//    }
//
//    public void setTblUserByEmployeeId(TblUser tblUserByEmployeeId) {
//        this.tblUserByEmployeeId = tblUserByEmployeeId;
//    }


    @ManyToOne
    @JoinColumn(name = "employeeID", referencedColumnName = "userID")
    public TblUser getTblUserByEmployeeId() {
        return employee;
    }

    public void setTblUserByEmployeeId(TblUser tblUserByEmployeeId) {
        this.employee = tblUserByEmployeeId;
    }

//    @ManyToOne
//    @JoinColumn(name = "customerID", referencedColumnName = "userID", nullable = false)
//    public TblUser getTblUserByCustomerId() {
//        return tblUserByCustomerId;
//    }
//
//    public void setTblUserByCustomerId(TblUser tblUserByCustomerId) {
//        this.tblUserByCustomerId = tblUserByCustomerId;
//    }

    @ManyToOne
    @JoinColumn(name = "customerID", referencedColumnName = "userID", nullable = false)
    public TblUser getTblUserByCustomerId() {
        return customer;
    }

    public void setTblUserByCustomerId(TblUser tblUserByCustomerId) {
        this.customer = tblUserByCustomerId;
    }

//    @OneToMany(mappedBy = "tblOrderByTblOrderOrderId")
//    public Collection<TblOrderdetails> getTblOrderdetailsByOrderId() {
//        return tblOrderdetailsByOrderId;
//    }
//
//    public void setTblOrderdetailsByOrderId(Collection<TblOrderdetails> tblOrderdetailsByOrderId) {
//        this.tblOrderdetailsByOrderId = tblOrderdetailsByOrderId;
//    }
}
