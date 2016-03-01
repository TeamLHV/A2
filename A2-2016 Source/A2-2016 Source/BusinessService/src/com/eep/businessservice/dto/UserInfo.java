/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.businessservice.dto;

import com.eep.datarepository.dto.UserDTO;
/**
 *
 * @author Tang
 */
public class UserInfo extends UserDTO {
    private String session;
    
    public UserInfo() {
    }
    
    public UserInfo(String username, String password, String firstname, String lastname, String department){
        this.setUsername(username);
        this.setPassword(password);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setDepartment(department);
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
