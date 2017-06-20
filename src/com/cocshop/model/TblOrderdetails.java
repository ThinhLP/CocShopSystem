package com.cocshop.model;

import javax.persistence.*;

/**
 * Created by Nguyen Cong Chinh on 6/19/2017.
 */
@Entity
@Table(name = "tbl_orderdetails", schema = "cocshop", catalog = "")
public class TblOrderdetails {
    private int id;
    private Integer quantity;
    private Double price;
    private TblOrder tblOrderByTblOrderOrderId;
    private TblProduct tblProductByTblProductProductId;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblOrderdetails that = (TblOrderdetails) o;

        if (id != that.id) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "tbl_order_orderID", referencedColumnName = "orderID", nullable = false)
    public TblOrder getTblOrderByTblOrderOrderId() {
        return tblOrderByTblOrderOrderId;
    }

    public void setTblOrderByTblOrderOrderId(TblOrder tblOrderByTblOrderOrderId) {
        this.tblOrderByTblOrderOrderId = tblOrderByTblOrderOrderId;
    }

    @ManyToOne
    @JoinColumn(name = "tbl_product_productID", referencedColumnName = "productID", nullable = false)
    public TblProduct getTblProductByTblProductProductId() {
        return tblProductByTblProductProductId;
    }

    public void setTblProductByTblProductProductId(TblProduct tblProductByTblProductProductId) {
        this.tblProductByTblProductProductId = tblProductByTblProductProductId;
    }
}
