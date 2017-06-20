package com.cocshop.repository;

import com.cocshop.model.TblProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Nguyen Cong Chinh on 6/19/2017.
 */
public interface ProductRepository extends CrudRepository<TblProduct, Integer> {
    List<TblProduct> findAll();

    @Query(value = "Select p FROM TblProduct  p where p.deleted=false ")
    List<TblProduct> listProduct();


}
