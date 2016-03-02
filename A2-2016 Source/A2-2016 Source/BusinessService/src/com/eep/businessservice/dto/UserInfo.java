/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.businessservice.dto;

import com.eep.datarepository.dto.UserDTO;
import java.sql.Timestamp;
/**
 *
 * @author Tang
 */
public class UserInfo extends UserDTO {

    public UserInfo() {
    }
    
    public class UserSession {
        private final String sessionKey;
        private final Timestamp timestamp;
        
        public UserSession(String session, Timestamp timestamp){
            this.sessionKey = session;
            this.timestamp = timestamp;
        }
        
        public String getSessionKey() {
            return sessionKey;
        }
        
        public Timestamp getTimestamp() {
            return timestamp;
        }
    }
    
    private UserSession usession;
    
    public UserInfo(String username, String password, String firstname, String lastname, String department){
        this.setUsername(username);
        this.setPassword(password, true);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setDepartment(department);
        
    }

    public UserSession getSession() {
        return usession;
    }
    
    public void setSession(String session, Timestamp timestamp) {
        this.usession = new UserSession(session, timestamp);
    }
}
