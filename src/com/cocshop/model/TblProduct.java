package com.cocshop.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import com.cocshop.View.view;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Nguyen Cong Chinh on 6/19/2017.
 */
@Entity
@Table(name = "tbl_product", schema = "cocshop", catalog = "")
public class TblProduct {
    @JsonView(view.listProduct.class)
    private int productId;
    @JsonView(view.listProduct.class)
    private String productName;
    @JsonView(view.listProduct.class)
    private int quantity;
    @JsonView(view.listProduct.class)
    private double price;
    @JsonView(view.listProduct.class)
    private String createAt;
    @JsonView(view.listProduct.class)
    private String updateAt;
    @JsonView(view.listProduct.class)
    private String description;
    private Collection<TblOrderdetails> tblOrderdetailssByProductId;
    @JsonView(view.listProduct.class)
    private TblCategory tblCategoryByTblCategoryCategoryId;
    private Boolean deleted;


    @Id
    @Column(name = "productID")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "productName")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "createAt")
    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    @Basic
    @Column(name = "updateAt")
    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblProduct that = (TblProduct) o;

        if (productId != that.productId) return false;
        if (quantity != that.quantity) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = productId;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + quantity;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tblProductByTblProductProductId")
    public Collection<TblOrderdetails> getTblOrderdetailssByProductId() {
        return tblOrderdetailssByProductId;
    }

    public void setTblOrderdetailssByProductId(Collection<TblOrderdetails> tblOrderdetailssByProductId) {
        this.tblOrderdetailssByProductId = tblOrderdetailssByProductId;
    }

    @ManyToOne
    @JoinColumn(name = "tbl_category_categoryID", referencedColumnName = "categoryID", nullable = false)
    public TblCategory getTblCategoryByTblCategoryCategoryId() {
        return tblCategoryByTblCategoryCategoryId;
    }

    public void setTblCategoryByTblCategoryCategoryId(TblCategory tblCategoryByTblCategoryCategoryId) {
        this.tblCategoryByTblCategoryCategoryId = tblCategoryByTblCategoryCategoryId;
    }



    @Basic
    @Column(name = "deleted")
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
