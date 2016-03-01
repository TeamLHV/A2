/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.businessservice.security;

import com.eep.businessservice.dto.UserInfo;
import com.eep.datarepository.dto.UserDTO;
import com.eep.datarepository.impl.UserDAO;
import com.eep.datarepository.util.securityUtil;
import java.lang.management.ManagementFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tang
 */
public class Authentication {
    public UserInfo authenticate(String username, String password){
        UserDAO query = new UserDAO();
        String encryptedPwd = securityUtil.MD5Encryption(password);
        UserDTO result = query.queryByUsernameAndPassword(username, encryptedPwd);
        
        if (result != null){
            String session = ManagementFactory.getRuntimeMXBean().getName();
            
            UserInfo ui = new UserInfo();
            ui.setFirstname(result.getFirstname());
            ui.setLastname(result.getLastname());
            ui.setDepartment(result.getDepartment());
            ui.setUsername(result.getUsername());
            ui.setSession(session);
            
            return ui;
        }
        
        return null;
    }
}
