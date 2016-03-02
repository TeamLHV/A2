/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository.impl;

import com.eep.businessservice.dto.UserInfo;
import com.eep.datarepository.ISessionDAO;
import com.eep.datarepository.dto.SessionDTO;
import com.eep.datarepository.util.dbUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    private final UserInfo userInfo;
    
    public SessionDAO(UserInfo userInfo){
        this.c = SessionDTO.class;
        this.database = Constants.DATABASE_AUTH;
        this.table = Constants.TABLE_SESSIONS;
        this.userInfo = userInfo;
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
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbUtil.closeConn(DBConn);
        }

        return result;
    }

    @Override
    public int insert(SessionDTO dto) {
         try {
             
             SessionDTO sessDTO = queryByUID(dto.getU_id());
             
             if (sessDTO != null){
                if (sessDTO.getTimestamp().after(dto.getTimestamp())){
                    return -1;
                } 
                
                 return update(dto);
                 
             } else {
                Statement s = dbUtil.createStatement(database);
                String sql = "INSERT INTO " + table + "(upid, u_id, timestamp) VALUES ('" + dto.getUpid() + "'," + dto.getU_id() + ",'" + dto.getTimestamp() + "');";
                return s.executeUpdate(sql);
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } finally {
            dbUtil.closeConn(DBConn);
        }
    }

    @Override
    public int update(SessionDTO dto) {
        try {
            String sql = "UPDATE " + table + " set timestamp='" + dto.getTimestamp() + "', upid='" + dto.getUpid() + "' where u_id='" + dto.getU_id() + "';";
            DBConn = DriverManager.getConnection(dbUtil.getConnString(database), Constants.USER_NAME, Constants.PASSWORD);
            PreparedStatement s = DBConn.prepareStatement(sql);
            
            return s.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } finally {
            dbUtil.closeConn(DBConn);
        }
    }

    @Override
    public int delete(SessionDTO dto) {
        try {
            Statement s = dbUtil.createStatement(database);
            String sql = "DELETE FROM " + table + " WHERE u_id = '" + dto.getU_id() + "';";
            return s.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } finally {
            dbUtil.closeConn(DBConn);
        }
    }
    
    public Boolean checkSession(UserInfo userInfo) {
        try {
            Statement s = dbUtil.createStatement(database);
            ResultSet rs = s.executeQuery("select * from " + table + " where u_id = " + userInfo.getU_id() + ";");
            
            while (rs.next()) {
                Timestamp ts = rs.getTimestamp("timestamp");
                
                if (ts.after(userInfo.getSession().getTimestamp()))
                    return false;
                else
                    return true;
            }
            
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            dbUtil.closeConn(DBConn);
        }
    }

    @Override
    public SessionDTO queryByUID(Integer u_id) {
        SessionDTO sessionDTO = null;
        try {
            Statement s = dbUtil.createStatement(database);
            ResultSet rs = s.executeQuery("select * from " + table + " where u_id='" + u_id + "';");
            if (rs.next()) {
                sessionDTO = (SessionDTO) c.newInstance();
                sessionDTO.setS_id(rs.getInt("s_id"));
                sessionDTO.setTimestamp(rs.getTimestamp("timestamp"));
                sessionDTO.setU_id(rs.getInt("u_id"));
                sessionDTO.setUpid(rs.getString("upid"));
                
                return sessionDTO;
            }
            
            return null;
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SessionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            dbUtil.closeConn(DBConn);
        }
    }
}
