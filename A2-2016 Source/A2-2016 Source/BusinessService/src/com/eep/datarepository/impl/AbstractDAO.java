/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository.impl;

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

/**
 *
 * @author zhongzhu
 * @param <DTO> : can be TreeDTO, ShrubDTO, and etc.
 */
public abstract class AbstractDAO<DTO extends InventoryItemDTO> {

    private static final String PASSWORD = "remote_pass";
    private static final String USER_NAME = "remote";

    private Connection DBConn = null;
    private final Class c;
    private final String database;
    private final String table;

    /**
     *
     * @param c type of DTO
     * @param database database name
     * @param table table name
     */
    protected AbstractDAO(Class c, String database, String table) {
        this.c = c;
        this.database = database;
        this.table = table;
    }

    protected List<DTO> queryAll() {
        List<DTO> result = new ArrayList<>();
        DTO temp;
        try {
            Statement s = createStatement();
            ResultSet rs = s.executeQuery("select * from " + table);
            while (rs.next()) {
                temp = (DTO) c.newInstance();
                constructInventoryItem(temp, rs);
                result.add(temp);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConn();
        }

        return result;
    }

    protected DTO queryByCode(String id) {
        DTO dto = null;
        try {
            Statement s = createStatement();
            ResultSet rs = s.executeQuery("select * from " + table + " where product_code='" + id + "'");
            if (rs.next()) {
                dto = (DTO) c.newInstance();
                constructInventoryItem(dto, rs);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConn();
        }
        return dto;
    }

    protected void insert(DTO dto) {
        try {
            Statement s = createStatement();
            String sql = "INSERT INTO " + table + " (product_code, "
                    + "description, quantity, price) VALUES ( '"
                    + dto.getProductCode() + "', " + "'" + dto.getDescription() + "', "
                    + dto.getQuantity() + ", " + dto.getPrice() + ");";
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConn();
        }
    }

    protected void deleteByProductCode(String productCode) {
        try {
            Statement s = createStatement();
            String sql = "DELETE FROM " + table + " WHERE product_code = '" + productCode + "';";
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConn();
        }
    }

    protected void update(DTO dto) {
        try {
            String sql = "UPDATE " + table + " set description=?, quantity=?, price=? where product_code=?";
            DBConn = DriverManager.getConnection(getConnString(), USER_NAME, PASSWORD);
            PreparedStatement s = DBConn.prepareStatement(sql);
            s.setString(1, dto.getDescription());
            s.setInt(2, dto.getQuantity());
            s.setDouble(3, dto.getPrice());
            s.setString(4, dto.getProductCode());
            s.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConn();
        }
    }

    private Statement createStatement() throws SQLException {
        DBConn = DriverManager.getConnection(getConnString(), USER_NAME, PASSWORD);
        Statement s = DBConn.createStatement();
        return s;
    }

    private String getConnString() {
        return "jdbc:mysql://localhost:3306/" + database;
    }

    private void closeConn() {
        try {
            DBConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void constructInventoryItem(InventoryItemDTO dto, ResultSet rs) throws SQLException {
        dto.setProductCode(rs.getString(1));
        dto.setDescription(rs.getString(2));
        dto.setQuantity(rs.getInt(3));
        dto.setPrice(rs.getDouble(4));
    }
}
