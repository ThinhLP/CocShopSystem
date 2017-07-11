package com.cocshop.services;

import com.cocshop.dto.RegisterResponse;
import com.cocshop.dto.UserDto;
import com.cocshop.model.TblRole;
import com.cocshop.model.TblUser;
import com.cocshop.repository.RoleRepository;
import com.cocshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ken on 7/4/2017.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public RegisterResponse register(String username, String password, String firstName, String lastName, String email, String birthday, int role, String phone) {
        RegisterResponse response = new RegisterResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        List<String> msgError = new ArrayList<>();
        username = username.toLowerCase();
        email = email.toLowerCase();
        if (userRepository.findTblUserByUsername(username) != null) {
            msgError.add("This username is existed.");
        }
        if (userRepository.findTblUserByEmail(email) != null) {
            msgError.add("This email is existed.");
        }
        if (!msgError.isEmpty()) {
            String[] msgErrorArray = new String[msgError.size()];
            msgErrorArray = msgError.toArray(msgErrorArray);
            response.setMessages(msgErrorArray);
            return response;
        }
        TblRole tblRole = roleRepository.findOne(role);
        TblUser newUser = new TblUser(username, password, email, firstName, lastName, birthday,tblRole,false, phone);
        userRepository.save(newUser);
        return new RegisterResponse(HttpStatus.OK.value(), null);
    }

    public UserDto checkLogin(String username, String password) {
        TblUser user = userRepository.checkLogin(username, password);
        if (user != null) {
            return convertToUserDto(user);
        }
        return null;
    }

    public UserDto convertToUserDto(TblUser user) {
        return new UserDto(user.getUserId(), user.getUsername(),user.getEmail(), user.getFirstname(), user.getLastname(),
                user.getBirthdate(), user.getTblRoleByTblRoleRoleId().getRoleId(), user.getPhone());
    }
}
