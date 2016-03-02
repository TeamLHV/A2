/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.businessservice.security;

import com.eep.businessservice.dto.UserInfo;
import com.eep.businessservice.dto.UserInfo.UserSession;
import com.eep.datarepository.dto.SessionDTO;
import com.eep.datarepository.dto.UserDTO;
import com.eep.datarepository.impl.SessionDAO;
import com.eep.datarepository.impl.UserDAO;
import com.eep.datarepository.util.dbUtil;
import com.eep.datarepository.util.securityUtil;
import java.lang.management.ManagementFactory;
import java.sql.Timestamp;

/**
 *
 * @author Tang
 */
public class Authentication {
    public UserInfo authenticate(String username, String password){
        UserDAO query = new UserDAO(null);
        String encryptedPwd = securityUtil.MD5Encryption(password);
        UserDTO result = query.queryByUsernameAndPassword(username, encryptedPwd);
        
        if (result != null){
            String session = ManagementFactory.getRuntimeMXBean().getName();
            
            UserInfo ui = new UserInfo();
            ui.setFirstname(result.getFirstname());
            ui.setLastname(result.getLastname());
            ui.setDepartment(result.getDepartment());
            ui.setUsername(result.getUsername());
            ui.setU_id(result.getU_id());
            
            if (addSession(ui, session) != -1){
                return ui;
            } 
        }
        
        return null;
    }
    
    private int addSession(UserInfo userInfo, String session) {
        Timestamp ts = dbUtil.generateTimeStamp();
        SessionDTO sDTO = new SessionDTO();
        sDTO.setUpid(session);
        sDTO.setTimestamp(ts);
        sDTO.setU_id(userInfo.getU_id());
        
        userInfo.setSession(session, ts);
        
        SessionDAO sDAO = new SessionDAO(userInfo);
        return sDAO.insert(sDTO);
    }
    
    public Boolean checkSession(UserInfo userInfo) {
        SessionDAO sDAO = new SessionDAO(userInfo);
        return sDAO.checkSession(userInfo);
    }
}
