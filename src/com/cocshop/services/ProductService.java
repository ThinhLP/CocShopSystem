package com.cocshop.services;

import com.cocshop.dto.ProductDto;
import com.cocshop.model.TblProduct;
import com.cocshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ken on 7/11/2017.
 */
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    /**  For Mobile - ThinhLP **/
    public List<ProductDto> getProductDtoList() {
        List<TblProduct> products = productRepository.listProduct();
        if (products == null || products.isEmpty()) {
            return null;
        }

        List<ProductDto> result = new ArrayList<>();
        for (TblProduct product: products) {
            result.add(new ProductDto(product));
        }
        return result;
    }
}
