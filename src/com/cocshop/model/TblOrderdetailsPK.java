package com.cocshop.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Nguyen Cong Chinh on 6/25/2017.
 */
@Embeddable
public class TblOrderdetailsPK implements Serializable {
    private Integer tbl_Order_OrderId;
    private Integer tbl_Product_ProductId;

    @Column(name = "tbl_order_orderID",nullable = false)
    public Integer getTbl_Order_OrderId() {
        return tbl_Order_OrderId;
    }


    @Column(name="tbl_product_productID",nullable = false)
    public void setTbl_Order_OrderId(Integer tbl_Order_OrderId) {
        this.tbl_Order_OrderId = tbl_Order_OrderId;
    }

    public Integer getTbl_Product_ProductId() {
        return tbl_Product_ProductId;
    }

    public void setTbl_Product_ProductId(Integer tbl_Product_ProductId) {
        this.tbl_Product_ProductId = tbl_Product_ProductId;
    }



}
