/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository;

import com.eep.businessservice.dto.UserInfo;
import com.eep.datarepository.dto.OrderDTO;
import com.eep.datarepository.dto.TreeDTO;
import com.eep.datarepository.dto.UserDTO;
import com.eep.datarepository.impl.OrdersDAO;
import com.eep.datarepository.impl.TreesDAO;
import com.eep.datarepository.impl.UserDAO;
import com.eep.datarepository.util.dbUtil;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author zhongzhu
 */
public class DataRepositoryTests {
    
    private UserInfo admin;
    private String adminSession = "adminsessionmock";
    
    public DataRepositoryTests() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.admin = new UserInfo();
        
        UserDAO adminDAO = new UserDAO(admin);
        UserDTO adminDTO = adminDAO.queryByUsername("admin");
        
        this.admin.setU_id(adminDTO.getU_id());
        this.admin.setSession(adminSession, dbUtil.generateTimeStamp());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testTreeQuery() {
        
        final TreesDAO treeDAO = new TreesDAO(admin);

        TreeDTO dto = new TreeDTO();
        dto.setProductCode("EF002");
        dto.setDescription("Hello");
        dto.setQuantity(10);
        dto.setPrice(10.5);
        treeDAO.insert(dto);

        TreeDTO query = treeDAO.queryByProductCode("EF002");
        assertEquals(query.getProductCode(), "EF002");
        assertEquals(query.getDescription(), "Hello");
        assertEquals((int) query.getQuantity(), 10);
        assertEquals((double) query.getPrice(), 10.5, 0.1);

        dto.setQuantity(9);
        treeDAO.update(dto);
        query = treeDAO.queryByProductCode("EF002");
        assertEquals(query.getProductCode(), "EF002");
        assertEquals(query.getDescription(), "Hello");
        assertEquals((int) query.getQuantity(), 9);
        assertEquals((double) query.getPrice(), 10.5, 0.1);

        treeDAO.deleteByProductCode("EF002");
        query = treeDAO.queryByProductCode("EF002");
        assertNull(query);
    }

    @Test
    public void testOrders() {
        OrdersDAO ordersDAO = new OrdersDAO(admin);
        OrderDTO newOrder = new OrderDTO();
        newOrder.setOrderDate("02/26/2016 5:5:45");
        newOrder.setFirstName("Zhong");
        newOrder.setLastName("Zhu");
        newOrder.setAddress("2715 Murray");
        newOrder.setPhone("4129615668");
        newOrder.setTotalCost(500.0);
        newOrder.setShipped(false);
        newOrder.setOrderTable("order2340197999");
        ordersDAO.insertOrder(newOrder);

        OrderDTO query = ordersDAO.queryOrderByOrderID(newOrder.getOrderID());
        assertEquals(query.getOrderDate(), newOrder.getOrderDate());
        assertEquals(query.getFirstName(), newOrder.getFirstName());
        assertEquals(query.getLastName(), newOrder.getLastName());
        assertEquals(query.getAddress(), newOrder.getAddress());
        assertEquals(query.getPhone(), newOrder.getPhone());
        assertEquals(query.getTotalCost(), newOrder.getTotalCost(), 0.01);
        assertEquals(query.getShipped(), newOrder.getShipped());
        assertEquals(query.getOrderTable(), newOrder.getOrderTable());

        query.setShipped(true);
        ordersDAO.updateOrder(query);
        query = ordersDAO.queryOrderByOrderID(newOrder.getOrderID());
        assertEquals(query.getOrderDate(), newOrder.getOrderDate());
        assertEquals(query.getFirstName(), newOrder.getFirstName());
        assertEquals(query.getLastName(), newOrder.getLastName());
        assertEquals(query.getAddress(), newOrder.getAddress());
        assertEquals(query.getPhone(), newOrder.getPhone());
        assertEquals(query.getTotalCost(), newOrder.getTotalCost(), 0.01);
        assertEquals(query.getShipped(), true);
        assertEquals(query.getOrderTable(), newOrder.getOrderTable());

        ordersDAO.deleteOrderByID(newOrder.getOrderID());
        query = ordersDAO.queryOrderByOrderID(newOrder.getOrderID());
        assertNull(query);
    }
    
    @Test
    public void testUsers(){
        UserDAO ud = new UserDAO(admin);
        UserDTO ut = new UserDTO();
        ut.setDepartment("CMU");
        ut.setFirstname("Tang");
        ut.setLastname("Lekhaka");
        ut.setPassword("d", true);
        ut.setUsername("plekhaka");
        ud.insert(ut);
        
        UserDTO result = ud.queryByUsername("plekhaka");
        assertEquals(result.getDepartment(), ut.getDepartment());
        assertEquals(result.getFirstname(), ut.getFirstname());
        assertEquals(result.getLastname(), ut.getLastname());
        assertEquals(result.getPassword(), ut.getPassword());
        
        ud.deleteByUsername(result.getUsername());
        ut = ud.queryByUsername(result.getUsername());
        assertNull(ut);
    }
}
