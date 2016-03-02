/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository.impl;

import com.eep.businessservice.dto.UserInfo;
import com.eep.businessservice.security.Authentication;
import com.eep.datarepository.dto.OrderDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.eep.datarepository.IOrdersDAO;
import com.eep.datarepository.dto.OrderTableItemDTO;
import java.sql.PreparedStatement;

/**
 *
 * @author zhongzhu
 */
public class OrdersDAO implements IOrdersDAO {

    private Connection DBConn = null;
    private UserInfo userInfo = new UserInfo();
    private Authentication auth = new Authentication();
    
    public OrdersDAO(UserInfo userInfo){
        this.userInfo = userInfo;
    }

    @Override
    public List<OrderDTO> queryAllOrders() {
        if (!auth.checkSession(userInfo)){
            return null;
        }
        
        List<OrderDTO> result = new ArrayList<>();
        OrderDTO dto;
        try {
            Statement s = createStatement();
            ResultSet rs = s.executeQuery("select * from " + Constants.TABLE_ORDER);
            while (rs.next()) {
                dto = constructOrder(rs);
                result.add(dto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConn();
        }

        return result;
    }

    @Override
    public OrderDTO queryOrderByOrderID(Long orderID) {
        OrderDTO dto = null;
        try {
            Statement s = createStatement();
            ResultSet rs = s.executeQuery("select * from " + Constants.TABLE_ORDER + " where order_id='" + orderID + "'");
            if (rs.next()) {
                dto = constructOrder(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConn();
        }
        return dto;
    }

    @Override
    public void insertOrder(OrderDTO order) {
        try {
            String SQLstatement = ("INSERT INTO orders (order_date, " + "first_name, "
                    + "last_name, address, phone, total_cost, shipped, "
                    + "ordertable) VALUES ( '" + order.getOrderDate() + "', "
                    + "'" + order.getFirstName() + "', " + "'" + order.getLastName() + "', "
                    + "'" + order.getAddress() + "', " + "'" + order.getPhone() + "', "
                    + order.getTotalCost() + ", " + order.getShipped() + ", '" + order.getOrderTable() + "' );");

            DBConn = DriverManager.getConnection(getConnString(), Constants.USER_NAME, Constants.PASSWORD);
            PreparedStatement statement = DBConn.prepareStatement(SQLstatement, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setOrderID(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating order failed, no ID obtained.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConn();
        }
    }

    public void deleteOrderByID(Long orderID) {
        try {
            String SQLstatement = ("DELETE FROM orders where order_id='" + orderID + "'");
            Statement s = createStatement();
            s.executeUpdate(SQLstatement);
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConn();
        }
    }

    @Override
    public void updateOrder(OrderDTO order) {
        try {
            String sql = "UPDATE " + Constants.TABLE_ORDER
                    + " set order_date=?, first_name=?, last_name=?, address=?, phone=?, total_cost=?, shipped=?, ordertable=? "
                    + "where order_id=?";
            DBConn = DriverManager.getConnection(getConnString(), Constants.USER_NAME, Constants.PASSWORD);
            PreparedStatement s = DBConn.prepareStatement(sql);
            s.setString(1, order.getOrderDate());
            s.setString(2, order.getFirstName());
            s.setString(3, order.getLastName());
            s.setString(4, order.getAddress());
            s.setString(5, order.getPhone());
            s.setDouble(6, order.getTotalCost());
            s.setBoolean(7, order.getShipped());
            s.setString(8, order.getOrderTable());
            s.setLong(9, order.getOrderID());
            s.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractInventoryItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConn();
        }
    }

    private OrderDTO constructOrder(ResultSet rs) throws SQLException {
        OrderDTO dto;
        dto = new OrderDTO();
        dto.setOrderID(rs.getLong(1));
        dto.setOrderDate(rs.getString(2));
        dto.setFirstName(rs.getString(3));
        dto.setLastName(rs.getString(4));
        dto.setAddress(rs.getString(5));
        dto.setPhone(rs.getString(6));
        dto.setTotalCost(rs.getDouble(7));
        dto.setShipped(rs.getBoolean(8));
        dto.setOrderTable(rs.getString(9));
        return dto;
    }

    private String getConnString() {
        return "jdbc:mysql://" + Constants.DB_IP + ":" + Constants.DB_PORT + "/" + Constants.DATABASE_ORDER;
    }

    private void closeConn() {
        try {
            DBConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractInventoryItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Statement createStatement() throws SQLException {
        DBConn = DriverManager.getConnection(getConnString(), Constants.USER_NAME, Constants.PASSWORD);
        Statement s = DBConn.createStatement();
        return s;
    }

    @Override
    public void createOrderTable(String orderTableName) {
        try {
            String SQLstatement = ("CREATE TABLE " + orderTableName
                    + "(item_id int unsigned not null auto_increment primary key, "
                    + "product_id varchar(20), description varchar(80), "
                    + "item_price float(7,2) );");
            Statement s = createStatement();
            s.executeUpdate(SQLstatement);
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void insertIntoOrderTable(String orderTableName, OrderTableItemDTO item) {
        try {
            String SQLstatement = ("INSERT INTO " + orderTableName
                    + " (product_id, description, item_price) "
                    + "VALUES ( '" + item.getProductCode() + "', " + "'"
                    + item.getDescription() + "', " + item.getPrice() + " );");
            Statement s = createStatement();
            s.executeUpdate(SQLstatement);
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void dropOrderTable(String orderTableName) {
        try {
            String SQLstatement = "DROP TABLE " + orderTableName;
            createStatement().executeUpdate(SQLstatement);
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<OrderTableItemDTO> queryAllOrderItems(String orderTableName) {
        List<OrderTableItemDTO> result = new ArrayList<>();
        OrderTableItemDTO dto;
        try {
            Statement s = createStatement();
            ResultSet rs = s.executeQuery("select * from " + orderTableName);
            while (rs.next()) {
                dto = new OrderTableItemDTO();
                dto.setID(rs.getLong(1));
                dto.setProductCode(rs.getString(2));
                dto.setDescription(rs.getString(3));
                dto.setPrice(rs.getDouble(4));
                result.add(dto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConn();
        }

        return result;
    }

}
