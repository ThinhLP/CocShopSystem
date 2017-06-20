package com.cocshop.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Nguyen Cong Chinh on 6/19/2017.
 */
@Entity
@Table(name = "tbl_order", schema = "cocshop", catalog = "")
public class TblOrder {
    private int orderId;
    private String orderDate;
    private TblUser tblUserByTblUserUserId;
    private Collection<TblOrderdetails> tblOrderdetailssByOrderId;



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

    @ManyToOne
    @JoinColumn(name = "tbl_user_userID", referencedColumnName = "userID", nullable = false)
    public TblUser getTblUserByTblUserUserId() {
        return tblUserByTblUserUserId;
    }

    public void setTblUserByTblUserUserId(TblUser tblUserByTblUserUserId) {
        this.tblUserByTblUserUserId = tblUserByTblUserUserId;
    }

    @OneToMany(mappedBy = "tblOrderByTblOrderOrderId")
    public Collection<TblOrderdetails> getTblOrderdetailssByOrderId() {
        return tblOrderdetailssByOrderId;
    }

    public void setTblOrderdetailssByOrderId(Collection<TblOrderdetails> tblOrderdetailssByOrderId) {
        this.tblOrderdetailssByOrderId = tblOrderdetailssByOrderId;
    }
}
