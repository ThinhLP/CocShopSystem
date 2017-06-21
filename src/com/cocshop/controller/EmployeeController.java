package com.cocshop.controller;

import com.cocshop.View.view;
import com.cocshop.model.TblRole;
import com.cocshop.model.TblUser;
import com.cocshop.repository.RoleRepository;
import com.cocshop.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Nguyen Cong Chinh on 6/21/2017.
 */

@Controller
public class EmployeeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @JsonView(view.listAllEmployee.class)
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/api/employees")
    public List getEmployeeJson(){
        List<TblUser> listEmp = userRepository.listAllEmployee();
        return listEmp;
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/employees/update")
    public String updateEmployee (int userId, String username, String password, String firstname, String lastname, String mail, String birthdate){
        TblUser user = userRepository.findOne(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(mail);
        user.setBirthdate(birthdate);
        userRepository.save(user);
        return "";
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/employees/delete")
    public String deleteEmp (int empId){
        TblUser user = userRepository.findOne(empId);
        user.setDeleted(true);
        userRepository.save(user);
        return "";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/employees/create")
    public String createEmp (String username, String password, String firstname, String lastname, String mail, String birthdate){
        TblRole role = new TblRole();
        TblUser user = new TblUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(mail);
        user.setBirthdate(birthdate);
        user.setDeleted(false);
        user.setTblRoleByTblRoleRoleId(roleRepository.findOne(2));
        userRepository.save(user);
        return "";
    }
}
