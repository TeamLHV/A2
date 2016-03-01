/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.businessservice.security;

import com.eep.businessservice.dto.UserInfo;
import com.eep.datarepository.dto.UserDTO;
import com.eep.datarepository.impl.UserDAO;
import java.lang.management.ManagementFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tang
 */
public final class Authentication {
    public static UserInfo authenticate(String username, String password){
        UserDAO query = new UserDAO();
        String encryptedPwd = MD5Encryption(password);
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
    
    public static String MD5Encryption(String text){
        
        try {
            if (text.isEmpty()){
                return "";
            }
            
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte byteData[] = md.digest();
            
            StringBuilder buffer = new StringBuilder();
             for (int i = 0; i < byteData.length; i++){
                buffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));   
             }
             
             return buffer.toString();
        } catch (NoSuchAlgorithmException | NullPointerException ex) {
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
