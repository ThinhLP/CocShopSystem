package com.cocshop.dto;

import com.cocshop.model.TblCategory;

/**
 * Created by Nguyen Cong Chinh on 7/11/2017.
 */
public class CategoryDto {
    private int categoryId;
    private String categoryName;
    private String description;

    public CategoryDto() {
    }

    public CategoryDto(TblCategory category) {
        categoryId = category.getCategoryId();
        categoryName = category.getCategoryName();
        description = category.getDescription();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
