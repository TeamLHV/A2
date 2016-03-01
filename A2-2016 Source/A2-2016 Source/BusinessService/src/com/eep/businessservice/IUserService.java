/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.businessservice;

import com.eep.businessservice.dto.UserInfo;

/**
 *
 * @author Tang
 */
public interface IUserService {
    UserInfo createUser(String username, String password, String firstname, String lastname, String department);
    int checkUsernameAvailable(String username);
    UserInfo authenticate(String username, String password);
}
