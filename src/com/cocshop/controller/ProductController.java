package com.cocshop.controller;

import com.cocshop.View.view;
import com.cocshop.model.TblProduct;
import com.cocshop.repository.CategoryRepository;
import com.cocshop.repository.ProductRepository;
import com.cocshop.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Ken on 6/20/2017.
 */
@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @JsonView(view.listProduct.class)
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/api/products")
    public List getProductJson(){
        List<TblProduct> productList = productRepository.listProduct();
        return productList;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/product/update")
    public String updateProduct(int productId, String productName, int quantity, double price, String description){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        TblProduct product = productRepository.findOne(productId);
        product.setProductName(productName);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setDescription(description);
        product.setUpdateAt(dateFormat.format(date));
        productRepository.save(product);
        return "";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/product/delete")
    public String deleteProduct (int productId){
        TblProduct product = productRepository.findOne(productId);
        product.setDeleted(true);
        productRepository.save(product);
        return "";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/product/create")
    public String createNewProduct(String createProductName, int createQuantity, double createPrice, int createCategory, String createDescription ){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        TblProduct product = new TblProduct();
        product.setProductName(createProductName);
        product.setQuantity(createQuantity);
        product.setPrice(createPrice);
        product.setTblCategoryByTblCategoryCategoryId(categoryRepository.findOne(createCategory));
        product.setDescription(createDescription);
        product.setCreateAt(dateFormat.format(date));
        product.setDeleted(false);
        productRepository.save(product);
        return "";
    }
}