package com.cocshop.controller;

import com.cocshop.View.view;
import com.cocshop.model.TblCategory;
import com.cocshop.repository.CategoryRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Ken on 6/20/2017.
 */

@Controller
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @JsonView(view.categoryList.class)
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/api/categories")
    public List getCategory(){
        List<TblCategory> categoryList = categoryRepository.findAll();
        return categoryList;
    }
}
