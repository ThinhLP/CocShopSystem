package com.cocshop.model;

import com.cocshop.View.view;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Nguyen Cong Chinh on 6/19/2017.
 */
@Entity
@Table(name = "tbl_role", schema = "cocshop", catalog = "")
public class TblRole {
    @JsonView(view.listAllEmployee.class)
    private int roleId;
    @JsonView(view.listAllEmployee.class)
    private String roleName;
    private Collection<TblUser> tblUsersByRoleId;

    @Id
    @Column(name = "roleID")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "roleName")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblRole tblRole = (TblRole) o;

        if (roleId != tblRole.roleId) return false;
        if (roleName != null ? !roleName.equals(tblRole.roleName) : tblRole.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tblRoleByTblRoleRoleId")
    public Collection<TblUser> getTblUsersByRoleId() {
        return tblUsersByRoleId;
    }

    public void setTblUsersByRoleId(Collection<TblUser> tblUsersByRoleId) {
        this.tblUsersByRoleId = tblUsersByRoleId;
    }
}
