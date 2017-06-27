package com.cocshop.model;

import com.cocshop.View.view;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

/**
 * Created by Nguyen Cong Chinh on 6/19/2017.
 */
@Entity
@Table(name = "tbl_orderdetails", schema = "cocshop", catalog = "")
public class TblOrderdetails {

    @JsonView({view.listOrderDetailsForCustomerId.class, view.listOrderByDate.class, view.viewAllOrder.class})
    private Integer quantity;
    @JsonView({view.listOrderDetailsForCustomerId.class, view.listOrderByDate.class,view.viewAllOrder.class})
    private Double price;
    @JsonView({view.listOrderDetailsForCustomerId.class, view.listOrderByDate.class,view.viewAllOrder.class})
    private TblOrder tblOrderByTblOrderOrderId;
    @JsonView({view.listOrderDetailsForCustomerId.class, view.listOrderByDate.class,view.viewAllOrder.class})
    private TblProduct tblProductByTblProductProductId;
    private TblOrderdetailsPK pk;

    @EmbeddedId
    public TblOrderdetailsPK getPk() {
        return pk;
    }

    public void setPk(TblOrderdetailsPK pk) {
        this.pk = pk;
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

        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }



    @ManyToOne
    @JoinColumns({@JoinColumn(name = "tbl_order_orderID", referencedColumnName = "orderID", nullable = false, insertable = false, updatable = false)})
    public TblOrder getTblOrderByTblOrderOrderId() {
        return tblOrderByTblOrderOrderId;
    }

    public void setTblOrderByTblOrderOrderId(TblOrder tblOrderByTblOrderOrderId) {
        this.tblOrderByTblOrderOrderId = tblOrderByTblOrderOrderId;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "tbl_product_productID", referencedColumnName = "productID", nullable = false, insertable = false, updatable = false)})
    public TblProduct getTblProductByTblProductProductId() {
        return tblProductByTblProductProductId;
    }

    public void setTblProductByTblProductProductId(TblProduct tblProductByTblProductProductId) {
        this.tblProductByTblProductProductId = tblProductByTblProductProductId;
    }

    @Override
    public int hashCode() {
        int result = quantity != null ? quantity.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
