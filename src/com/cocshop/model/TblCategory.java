package com.cocshop.model;

import javax.persistence.*;
import java.util.Collection;
import com.cocshop.View.view;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by Nguyen Cong Chinh on 6/19/2017.
 */
@Entity
@Table(name = "tbl_category", schema = "cocshop", catalog = "")
public class TblCategory {
    @JsonView({view.listProduct.class, view.categoryList.class, view.listOrderDetailsForCustomerId.class,view.searchProductByName.class})
    private int categoryId;
    @JsonView({view.listProduct.class, view.categoryList.class, view.listOrderDetailsForCustomerId.class, view.searchProductByName.class})
    private String categoryName;
    @JsonView(view.categoryList.class)
    private String description;
    private Collection<TblProduct> tblProductsByCategoryId;


    @Id
    @Column(name = "categoryID")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "categoryName")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

        TblCategory that = (TblCategory) o;

        if (categoryId != that.categoryId) return false;
        if (categoryName != null ? !categoryName.equals(that.categoryName) : that.categoryName != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryId;
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tblCategoryByTblCategoryCategoryId")
    public Collection<TblProduct> getTblProductsByCategoryId() {
        return tblProductsByCategoryId;
    }

    public void setTblProductsByCategoryId(Collection<TblProduct> tblProductsByCategoryId) {
        this.tblProductsByCategoryId = tblProductsByCategoryId;
    }




}
