package com.cocshop.controller;

import com.cocshop.model.TblUser;
import com.cocshop.repository.CategoryRepository;
import com.cocshop.repository.ProductRepository;
import com.cocshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Nguyen Cong Chinh on 6/19/2017.
 */

@Controller
@EnableWebMvc
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String login(String username, String password){
        TblUser user = userRepository.checkLogin(username, password);
        if(user != null){
            if(user.getTblRoleByTblRoleRoleId().getRoleId() == 1){
                return "redirect:/products";
            }
            if(user.getTblRoleByTblRoleRoleId().getRoleId() == 3){
                return "redirect: /orderPage";
            }
        }
        return "errorLogin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public String viewProduct() {
        return "adminPage";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/orderPage")
    public String orderTransactionPage(){
        return "orderTransaction";
    }

    
}
