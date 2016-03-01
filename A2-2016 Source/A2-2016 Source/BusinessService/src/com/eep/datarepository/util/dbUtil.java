/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository.util;

import com.eep.datarepository.impl.Constants;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tang
 */
public final class dbUtil {
    
    private static Connection DBConn = null;
    private dbUtil(){
    }
    
    public static String getConnString(String database) {
        return "jdbc:mysql://" + Constants.DB_IP + ":" + Constants.DB_PORT + "/" + database;
    }

    public static void closeConn(Connection DBConn) {
        try {
            if (DBConn != null) {
                DBConn.close();
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(Thread.currentThread().getStackTrace()[1].getClassName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Statement createStatement(String database) throws SQLException {
        DBConn = DriverManager.getConnection(getConnString(database), Constants.USER_NAME, Constants.PASSWORD);
        Statement s = DBConn.createStatement();
        return s;
    }
    
    
   public static void logLoginInfo(int username){
        try{
            Statement stmt = createStatement(Constants.DATABASE_AUTH);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String sqlQuery = "insert into " + Constants.TABLE_LOGGING + " values("+username+",'"+timestamp+"','Login')";
            stmt.executeUpdate(sqlQuery);            
        }catch(SQLException e){
            Logger.getLogger(Thread.currentThread().getStackTrace()[1].getClassName()).log(Level.SEVERE, null, e);
        }        
    }
    
    public static void logLogoutInfo(int username){
        try{
            Statement stmt = createStatement(Constants.DATABASE_AUTH);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String sqlQuery = "insert into " + Constants.TABLE_LOGGING + " values("+username+",'"+timestamp+"','Logout')";
            stmt.executeUpdate(sqlQuery);            
        }catch(SQLException e){
            Logger.getLogger(Thread.currentThread().getStackTrace()[1].getClassName()).log(Level.SEVERE, null, e);
        }        
    }
}
