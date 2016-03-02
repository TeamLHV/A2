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
import com.eep.datarepository.dto.SessionDTO;
import com.eep.datarepository.dto.UserDTO;
import com.eep.datarepository.impl.SessionDAO;
import com.eep.datarepository.util.dbUtil;
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
        Authentication auth = new Authentication();
        UserInfo ui = auth.authenticate(username, password);
        if (ui != null){
            dbUtil.logLoginInfo(ui.getUsername());
            return ui;
        }
        
        return null;
    }

    @Override
    public UserInfo createUser(String username, String password, String firstname, String lastname, String department) {
        UserInfo ui = new UserInfo();
        ui.setDepartment(department);
        ui.setFirstname(firstname);
        ui.setLastname(lastname);
        ui.setUsername(username);
        ui.setPassword(password, true);
        
        if (userDAO.insert(ui) != -1){
            return ui;
        } else {
            return null;
        }
    }

    @Override
    public int checkUsernameAvailable(String username) {
        return userDAO.checkUsernameAvailable(username);
    }

    @Override
    public void logOut(UserInfo userInfo) {
        SessionDAO sessionDAO = new SessionDAO(userInfo);
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setU_id(userInfo.getU_id());
        sessionDAO.delete(sessionDTO);
    }
}
