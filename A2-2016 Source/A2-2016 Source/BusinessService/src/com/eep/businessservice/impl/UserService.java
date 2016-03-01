/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.businessservice.impl;

import com.eep.businessservice.IUserService;
import com.eep.businessservice.dto.UserInfo;
import com.eep.datarepository.IUserDAO;
import com.eep.datarepository.impl.UserDAO;
import com.eep.businessservice.security.Authentication;
import com.eep.datarepository.dto.UserDTO;
/**
 *
 * @author Tang
 */
public class UserService implements IUserService{
    private final IUserDAO userDAO;
    
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public UserInfo authenticate(String username, String password) {
        UserInfo ud = Authentication.authenticate(username, password);
        return ud;
    }

    @Override
    public UserInfo createUser(String username, String password, String firstname, String lastname, String department) {
        UserDTO ud = new UserDTO();
        ud.setDepartment(department);
        ud.setFirstname(firstname);
        ud.setLastname(lastname);
        ud.setUsername(username);
        ud.setPassword(password);
        
        if (userDAO.insert(ud) == 1){
            return new UserInfo();
        } else {
            return null;
            
        }
    }

    @Override
    public int checkUsernameAvailable(String username) {
        return userDAO.checkUsernameAvailable(username);
    }
}
