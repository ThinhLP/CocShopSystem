package com.cocshop.repository;

import com.cocshop.model.TblUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Nguyen Cong Chinh on 6/19/2017.
 */
public interface UserRepository extends CrudRepository<TblUser, Integer> {

    @Query(value = "select p from TblUser p where p.username=:username and p.password=:password and p.deleted = false")
    public TblUser checkLogin(@Param("username") String username, @Param("password") String password);


    @Query(value = "select p from TblUser  p where p.tblRoleByTblRoleRoleId.roleId=2 and p.deleted = false")
    public List<TblUser> listAllEmployee();

    @Query(value = "select p from TblUser p where p.tblRoleByTblRoleRoleId.roleId=3 and p.deleted= false")
    public List<TblUser> listAllCustomer();

    @Query(value = "select p from TblUser p where p.tblRoleByTblRoleRoleId.roleId=2 and p.firstname like concat('%', :firstname, '%') and p.deleted=false ")
    public List<TblUser> searchByFirstName(@Param("firstname") String firstname);

    @Query(value = "select p from TblUser p where p.tblRoleByTblRoleRoleId.roleId=3 and p.firstname like concat('%', :firstname, '%') and p.deleted=false ")
    public List<TblUser> searchCustomerByFirstName(@Param("firstname") String firstname);

    TblUser findTblUserByUsername(String username);
}
