package com.cocshop.common;

import com.cocshop.dto.ProductDto;
import com.cocshop.model.TblProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ken on 7/4/2017.
 */
public class Utils {

    public static List<ProductDto> convertToProductDtoList(List<TblProduct> list) {
        List<ProductDto> result = new ArrayList<>();
        for (TblProduct product: list) {
            result.add(new ProductDto(product));
        }
        return result;
    }
}
