package com.cocshop.dto;

import com.cocshop.model.TblCategory;

/**
 * Created by Nguyen Cong Chinh on 7/11/2017.
 */
public class CategoryDto {
    private int categoryId;
    private String categoryName;

    public CategoryDto() {
    }

    public CategoryDto(TblCategory category) {
        categoryId = category.getCategoryId();
        categoryName = category.getCategoryName();
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
