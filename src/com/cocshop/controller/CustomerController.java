package com.cocshop.controller;

import com.cocshop.View.view;
import com.cocshop.model.TblUser;
import com.cocshop.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Nguyen Cong Chinh on 6/22/2017.
 */
@Controller
public class CustomerController  {

    @Autowired
    UserRepository userRepository;

    @JsonView(view.listAllCustomer.class)
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/api/customers")
    public ResponseEntity<List<TblUser>> getCustomer(){
        List<TblUser> list = userRepository.listAllCustomer();
        if(list == null || list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<TblUser>>(list,HttpStatus.OK);
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/customers/delete")
    public String deleteCustomer(int customerId){
        TblUser user = userRepository.findOne(customerId);
        user.setDeleted(true);
        userRepository.save(user);
        return "";
    }

    @JsonView(view.searchCusByFirstName.class)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/customers/searchValue")
    public List searchCusbyFirstName (String searchValue){
        List<TblUser> list = userRepository.searchCustomerByFirstName(searchValue);
        return list;
    }

}
