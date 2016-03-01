/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository.impl;
import com.eep.datarepository.IUserDAO;
import com.eep.datarepository.dto.UserDTO;
import com.eep.datarepository.util.dbUtil;
import static com.eep.datarepository.util.dbUtil.getConnString;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tang
 */
public class UserDAO implements IUserDAO{

   private Connection DBConn = null;

    private final Class c;
    private final String database;
    private final String table;
    
    public UserDAO(){
        this.c = UserDTO.class;
        this.database = Constants.DATABASE_AUTH;
        this.table = Constants.TABLE_USERS;
    }
    
    public UserDTO queryByUsername(String username) {
        UserDTO userDTO = null;
        try {
            Statement s = dbUtil.createStatement(database);
            ResultSet rs = s.executeQuery("select * from " + table + " where users.username='" + username + "';");
            if (rs.next()) {
                userDTO = (UserDTO) c.newInstance();
                constructUser(userDTO, rs);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            dbUtil.closeConn(DBConn);
        }
        
        return userDTO;
    }
    
    public UserDTO queryByUsernameAndPassword(String username, String password) {
        UserDTO userDTO = null;
        try {
            Statement s = dbUtil.createStatement(database);
            ResultSet rs = s.executeQuery("select * from " + table + " where username='" + username + "' and password ='" + password + "';");
            if (rs.next()) {
                userDTO = (UserDTO) c.newInstance();
                constructUser(userDTO, rs);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbUtil.closeConn(DBConn);
        }
        return userDTO;
    }
    
    public UserDTO queryBySessionKey(String sessionKey){
        UserDTO userDTO = null;
        try {
            Statement s = dbUtil.createStatement(database);
            ResultSet rs = s.executeQuery("select * from" + table + " where users.u_id=sessions.u_id and sessions.upid = '" + sessionKey + "';");
            if (rs.next()) {
                userDTO = (UserDTO) c.newInstance();
                constructUser(userDTO, rs);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbUtil.closeConn(DBConn);
        }
        return userDTO;
    }
    
    public void constructUser(UserDTO dto, ResultSet rs) throws SQLException {
        dto.setU_id(rs.getInt("u_id"));
        dto.setUsername(rs.getString("username"));
        dto.setDepartment(rs.getString("department"));
        dto.setFirstname(rs.getString("firstname"));
        dto.setLastname(rs.getString("lastname"));
        dto.setPassword(rs.getString("password"), false);
    }

    @Override
    public List<UserDTO> queryAll() {
        List<UserDTO> result = new ArrayList<>();
        UserDTO temp;
        try {
            Statement s = dbUtil.createStatement(database);
            ResultSet rs = s.executeQuery("select * from " + table);
            while (rs.next()) {
                temp = (UserDTO) c.newInstance();
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
    public int insert(UserDTO dto) {
         try {
            Statement s = dbUtil.createStatement(database);
            String sql = "INSERT INTO " + table + " (username, "
                    + "password, firstname, lastname, department) VALUES ( '"
                    + dto.getUsername() + "', '" + dto.getPassword() + "', '"
                    + dto.getFirstname() + "', '" + dto.getLastname() + "', '" + dto.getDepartment() + "');";
            return s.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } finally {
            dbUtil.closeConn(DBConn);
        }
    }

    @Override
    public int update(UserDTO user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteByUsername(String username) {
        try {
            Statement s = dbUtil.createStatement(database);
            System.out.println(username);
            String sql = "DELETE FROM " + table + " WHERE username = '" + username + "';";
            System.out.println(sql);
            return s.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } finally {
            dbUtil.closeConn(DBConn);
        }
    }

    @Override
    public int checkUsernameAvailable(String username) {
        try {
            //Statement s = dbUtil.createStatement(database);
            DBConn = DriverManager.getConnection(getConnString(database), Constants.USER_NAME, Constants.PASSWORD);
            Statement s = DBConn.createStatement();
            String sql = "SELECT * FROM " + table + " WHERE username='" + username + "';"; 
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()){
                return rs.getInt(1);
            }
                
            return s.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbUtil.closeConn(DBConn);
            return -1;
        }
    }
    
}
