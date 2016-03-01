/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository.impl;

import com.eep.datarepository.ISessionDAO;
import com.eep.datarepository.dto.SessionDTO;
import com.eep.datarepository.util.dbUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tang
 */
public class SessionDAO implements ISessionDAO{
    
    private Connection DBConn = null;

    private final Class c;
    private final String database;
    private final String table;
    
    public SessionDAO(){
        this.c = UserDAO.class;
        this.database = Constants.DATABASE_AUTH;
        this.table = Constants.TABLE_USERS;
    }
    
    @Override
    public List<SessionDTO> queryAll() {
        List<SessionDTO> result = new ArrayList<>();
        SessionDTO temp;
        try {
            Statement s = dbUtil.createStatement(database);
            ResultSet rs = s.executeQuery("select * from " + table);
            while (rs.next()) {
                temp = (SessionDTO) c.newInstance();
                result.add(temp);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbUtil.closeConn(DBConn);
        }

        return result;
    }

    @Override
    public int insert(SessionDTO dto) {
         try {
            Statement s = dbUtil.createStatement(database);
            long time = System.currentTimeMillis();
            Timestamp timestamp = new java.sql.Timestamp(time);
            String sql = "INSERT INTO " + table + " (upid, u_id, timestamp) VALUES ('" + dto.getUpid() + "','" + dto.getU_id() + "','" + timestamp + "');";
            return s.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbUtil.closeConn(DBConn);
            return -1;
        }
    }

    @Override
    public int update(SessionDTO user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(SessionDTO session) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
