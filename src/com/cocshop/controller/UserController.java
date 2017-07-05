package com.cocshop.controller;

import com.cocshop.common.Const;
import com.cocshop.dto.ErrorDto;
import com.cocshop.dto.UserDto;
import com.cocshop.model.TblUser;
import com.cocshop.repository.UserRepository;
import com.cocshop.services.UserService;
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
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/api/login")
    @ResponseBody
    public ResponseEntity<UserDto> checkLogin(String username, String password){
        TblUser user = userService.checkLogin(username,password);
        if(user != null){
            return new ResponseEntity<>(convertToUserDto(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/register")
    @ResponseBody
    public ResponseEntity<ErrorDto> registerUser(String username, String password, String firstName, String lastName, String email, String birthday, String phone) {
        ErrorDto error = userService.register(username, password, firstName, lastName, email, birthday, Const.APP_ROLE.USER, phone);
        if (error == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    public UserDto convertToUserDto(TblUser user) {
        return new UserDto(user.getUserId(), user.getUsername(),user.getEmail(), user.getFirstname(), user.getLastname(),
                user.getBirthdate(), user.getTblRoleByTblRoleRoleId().getRoleId(), user.getPhone());
    }
}
