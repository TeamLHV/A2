/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository;

import com.eep.datarepository.dto.UserDTO;
import java.util.List;

/**
 *
 * @author Tang
 */
public interface IUserDAO {
    List<UserDTO> queryAll();

    int insert(UserDTO user);

    int update(UserDTO user);
    
    int deleteByUsername(String username);
    
    int checkUsernameAvailable(String username);
}
