/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository;

import com.eep.datarepository.dto.SessionDTO;
import java.util.List;

/**
 *
 * @author Tang
 */
public interface ISessionDAO {
    List<SessionDTO> queryAll();

    int insert(SessionDTO session);

    int update(SessionDTO session);
    
    int delete(SessionDTO session);
    
}
