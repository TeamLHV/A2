/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository;

import com.eep.datarepository.dto.TreeDTO;
import com.eep.datarepository.impl.TreesDAO;
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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testTreeQuery() {
        final TreesDAO treeDAO = new TreesDAO();
        
        TreeDTO dto = new TreeDTO();
        dto.setProductCode("EF002");
        dto.setDescription("Hello");
        dto.setQuantity(10);
        dto.setPrice(10.5);
        treeDAO.insertTree(dto);

        TreeDTO query = treeDAO.queryTreesByProductCode("EF002");
        assertEquals(query.getProductCode(), "EF002");
        assertEquals(query.getDescription(), "Hello");
        assertEquals((int) query.getQuantity(), 10);
        assertEquals((double) query.getPrice(), 10.5, 0.1);

        dto.setQuantity(9);
        treeDAO.updateTree(dto);
        query = treeDAO.queryTreesByProductCode("EF002");
        assertEquals(query.getProductCode(), "EF002");
        assertEquals(query.getDescription(), "Hello");
        assertEquals((int) query.getQuantity(), 9);
        assertEquals((double) query.getPrice(), 10.5, 0.1);

        treeDAO.deleteTreeByProductCode("EF002");
        query = treeDAO.queryTreesByProductCode("EF002");
        assertNull(query);
    }
}
