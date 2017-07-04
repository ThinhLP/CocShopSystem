package com.cocshop.model;

import com.cocshop.View.view;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Nguyen Cong Chinh on 6/19/2017.
 */
@Entity
@Table(name = "tbl_user", schema = "cocshop", catalog = "")
public class TblUser {
    @JsonView({view.listAllEmployee.class, view.listAllCustomer.class,
            view.listOrderDetailsForCustomerId.class,view.listOrderByDate.class,
            view.searchEmployeeByFirstName.class,view.searchCusByFirstName.class,
            view.checkLogin.class,view.viewAllOrder.class,view.getOrderByOrderId.class})
    private int userId;
    @JsonView({view.listAllEmployee.class,view.listAllCustomer.class,
            view.listOrderDetailsForCustomerId.class,view.listOrderByDate.class,
            view.searchEmployeeByFirstName.class,view.searchCusByFirstName.class,
            view.checkLogin.class,view.viewAllOrder.class,view.getOrderByOrderId.class})
    private String username;
    @JsonView({view.listAllEmployee.class,view.listAllCustomer.class,
            view.listOrderDetailsForCustomerId.class,view.listOrderByDate.class,
            view.searchEmployeeByFirstName.class,view.searchCusByFirstName.class,
            view.checkLogin.class,view.viewAllOrder.class})
    private String password;
    @JsonView({view.listAllEmployee.class,view.listAllCustomer.class,
            view.listOrderDetailsForCustomerId.class,view.listOrderByDate.class,
            view.searchEmployeeByFirstName.class,view.searchCusByFirstName.class,
            view.checkLogin.class,view.viewAllOrder.class})
    private String email;
    @JsonView({view.listAllEmployee.class, view.listAllCustomer.class,
            view.listOrderDetailsForCustomerId.class,view.listOrderByDate.class,
            view.searchEmployeeByFirstName.class,view.searchCusByFirstName.class,
            view.checkLogin.class,view.viewAllOrder.class,view.getOrderByOrderId.class})
    private String firstname;
    @JsonView({view.listAllEmployee.class,view.listAllCustomer.class,
            view.listOrderDetailsForCustomerId.class,view.listOrderByDate.class,
            view.searchEmployeeByFirstName.class,view.searchCusByFirstName.class,
            view.checkLogin.class,view.viewAllOrder.class, view.getOrderByOrderId.class})
    private String lastname;
    @JsonView({view.listAllEmployee.class,view.listAllCustomer.class,
            view.listOrderDetailsForCustomerId.class,view.listOrderByDate.class,
            view.searchEmployeeByFirstName.class,view.searchCusByFirstName.class,
            view.checkLogin.class, view.viewAllOrder.class})
    private String birthdate;
    private Collection<TblOrder> tblOrdersByUserId;
    @JsonView({view.listAllEmployee.class, view.listAllCustomer.class,
            view.searchEmployeeByFirstName.class,view.searchCusByFirstName.class,view.checkLogin.class})
    private TblRole tblRoleByTblRoleRoleId;
    private Boolean deleted;

    public TblUser() {

    }

    public TblUser(String username, String password, String email, String firstname, String lastname, String birthdate, TblRole tblRoleByTblRoleRoleId, Boolean deleted) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.tblRoleByTblRoleRoleId = tblRoleByTblRoleRoleId;
        this.deleted = deleted;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "birthdate")
    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblUser tblUser = (TblUser) o;

        if (userId != tblUser.userId) return false;
        if (username != null ? !username.equals(tblUser.username) : tblUser.username != null) return false;
        if (password != null ? !password.equals(tblUser.password) : tblUser.password != null) return false;
        if (email != null ? !email.equals(tblUser.email) : tblUser.email != null) return false;
        if (firstname != null ? !firstname.equals(tblUser.firstname) : tblUser.firstname != null) return false;
        if (lastname != null ? !lastname.equals(tblUser.lastname) : tblUser.lastname != null) return false;
        if (birthdate != null ? !birthdate.equals(tblUser.birthdate) : tblUser.birthdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tblUserByEmployeeId")
    public Collection<TblOrder> getTblOrdersByUserId() {
        return tblOrdersByUserId;
    }

    public void setTblOrdersByUserId(Collection<TblOrder> tblOrdersByUserId) {
        this.tblOrdersByUserId = tblOrdersByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "tbl_role_roleID", referencedColumnName = "roleID", nullable = false)
    public TblRole getTblRoleByTblRoleRoleId() {
        return tblRoleByTblRoleRoleId;
    }

    public void setTblRoleByTblRoleRoleId(TblRole tblRoleByTblRoleRoleId) {
        this.tblRoleByTblRoleRoleId = tblRoleByTblRoleRoleId;
    }

    @Basic
    @Column(name = "deleted")
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }


}
