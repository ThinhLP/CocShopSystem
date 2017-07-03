package com.cocshop.controller;

import com.cocshop.dto.UserDto;
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.cocshop.View.view;

/**
 * Created by Nguyen Cong Chinh on 6/27/2017.
 */
@Controller
@EnableWebMvc
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @JsonView(view.loginResponse.class)
    @RequestMapping(method = RequestMethod.POST, value = "/api/login")
    @ResponseBody
    public ResponseEntity<UserDto> checkLogin(String username, String password){
        TblUser user = userRepository.checkLogin(username,password);
        if(user != null){
            return new ResponseEntity<>(convertToUserDto(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    public UserDto convertToUserDto(TblUser user) {
        return new UserDto(user.getUserId(), user.getUsername(),user.getEmail(), user.getFirstname(), user.getLastname(), user.getBirthdate(), user.getTblRoleByTblRoleRoleId().getRoleId());
    }
}
