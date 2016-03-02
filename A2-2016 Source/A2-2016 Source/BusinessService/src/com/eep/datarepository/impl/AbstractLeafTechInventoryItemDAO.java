/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository.impl;

import com.eep.businessservice.dto.UserInfo;
import com.eep.businessservice.security.Authentication;
import com.eep.datarepository.dto.InventoryItemDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.eep.datarepository.util.dbUtil;

/**
 *
 * @author zhongzhu
 * @param <DTO> : can be TreeDTO, ShrubDTO, and etc.
 */
public abstract class AbstractLeafTechInventoryItemDAO<DTO extends InventoryItemDTO> {

    private Connection DBConn = null;

    private final Class c;
    private final String database;
    private final String table;
    private final UserInfo userInfo;
    private final Authentication auth;
    /**
     *
     * @param c type of DTO
     * @param database database name
     * @param table table name
     */
    protected AbstractLeafTechInventoryItemDAO(Class c, String database, String table, UserInfo userInfo) {
        this.c = c;
        this.database = database;
        this.table = table;
        this.userInfo = userInfo;
        auth = new Authentication();
    }

    protected List<DTO> queryAll() {
        if (!auth.checkSession(userInfo)){
            return null;
        }
        
        List<DTO> result = new ArrayList<>();
        DTO temp;
        try {
            Statement s = dbUtil.createStatement(database);
            ResultSet rs = s.executeQuery("select * from " + table);
            while (rs.next()) {
                temp = (DTO) c.newInstance();
                constructInventoryItem(temp, rs);
                result.add(temp);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AbstractLeafTechInventoryItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbUtil.closeConn(DBConn);
        }

        return result;
    }

    protected DTO queryByCode(String id) {
        if (!auth.checkSession(userInfo)){
            return null;
        }
        
        DTO dto = null;
        try {
            Statement s = dbUtil.createStatement(database);
            ResultSet rs = s.executeQuery("select * from " + table + " where productid='" + id + "'");
            if (rs.next()) {
                dto = (DTO) c.newInstance();
                constructInventoryItem(dto, rs);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AbstractLeafTechInventoryItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbUtil.closeConn(DBConn);
        }
        return dto;
    }

    protected void insert(DTO dto) {
        if (!auth.checkSession(userInfo)){
            return;
        }
        
        try {
            Statement s = dbUtil.createStatement(database);
            String sql = "INSERT INTO " + table + " (productid, "
                    + "productdescription, productquantity, productprice) VALUES ( '"
                    + dto.getProductCode() + "', " + "'" + dto.getDescription() + "', "
                    + dto.getQuantity() + ", " + dto.getPrice() + ");";
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AbstractLeafTechInventoryItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbUtil.closeConn(DBConn);
        }
    }

    protected void deleteByProductCode(String productCode) {
        if (!auth.checkSession(userInfo)){
            return;
        }
        
        try {
            Statement s = dbUtil.createStatement(database);
            System.out.println(productCode);
            String sql = "DELETE FROM " + table + " WHERE productid = '" + productCode + "';";
            System.out.println(sql);
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AbstractLeafTechInventoryItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbUtil.closeConn(DBConn);
        }
    }

    protected void update(DTO dto) {
        if (!auth.checkSession(userInfo)){
            return;
        }
        
        try {
            String sql = "UPDATE " + table + " set productdescription=?, productquantity=?, productprice=? where productid=?";
            DBConn = DriverManager.getConnection(dbUtil.getConnString(database), Constants.USER_NAME, Constants.PASSWORD);
            PreparedStatement s = DBConn.prepareStatement(sql);
            s.setString(1, dto.getDescription());
            s.setInt(2, dto.getQuantity());
            s.setDouble(3, dto.getPrice());
            s.setString(4, dto.getProductCode());
            s.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractLeafTechInventoryItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbUtil.closeConn(DBConn);
        }
    }
    
    private void constructInventoryItem(InventoryItemDTO dto, ResultSet rs) throws SQLException {
        dto.setProductCode(rs.getString(1));
        dto.setDescription(rs.getString(2));
        dto.setQuantity(rs.getInt(3));
        dto.setPrice(rs.getDouble(4));
    }
}
