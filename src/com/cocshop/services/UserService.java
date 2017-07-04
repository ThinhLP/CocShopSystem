package com.cocshop.services;

import com.cocshop.dto.UserDto;
import com.cocshop.model.TblRole;
import com.cocshop.model.TblUser;
import com.cocshop.repository.RoleRepository;
import com.cocshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ken on 7/4/2017.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public TblUser register(String username, String password, String firstName, String lastName, String email, String birthday, int role) {
        if (userRepository.findTblUserByUsername(username) != null) {
            return null;
        }
        TblRole tblRole = roleRepository.findOne(role);
        TblUser newUser = new TblUser(username, password, email, firstName, lastName, birthday,tblRole,false);
        return userRepository.save(newUser);
    }

    public TblUser checkLogin(String username, String password) {
        return userRepository.checkLogin(username, password);
    }
}
