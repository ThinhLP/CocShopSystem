package com.cocshop.repository;

import com.cocshop.model.TblCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Nguyen Cong Chinh on 6/20/2017.
 */

public interface CategoryRepository extends CrudRepository<TblCategory, Integer> {
    List<TblCategory>findAll();
}
