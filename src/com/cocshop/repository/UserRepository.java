package com.cocshop.repository;

import com.cocshop.model.TblUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Nguyen Cong Chinh on 6/19/2017.
 */
public interface UserRepository extends CrudRepository<TblUser, Integer> {

    @Query(value = "select p from TblUser p where p.username=:username and p.password=:password")
    public TblUser checkLogin(@Param("username") String username, @Param("password") String password);
}
