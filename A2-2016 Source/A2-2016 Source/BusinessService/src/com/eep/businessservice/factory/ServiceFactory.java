/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.businessservice.factory;

import com.eep.businessservice.IInventoryService;
import com.eep.businessservice.IOrderService;
import com.eep.businessservice.impl.InventoryService;
import com.eep.businessservice.impl.OrderService;
import com.eep.datarepository.impl.CultureBoxDAO;
import com.eep.datarepository.impl.GenomicDAO;
import com.eep.datarepository.impl.ProcessingDAO;
import com.eep.datarepository.impl.ReferenceMaterialDAO;
import com.eep.datarepository.impl.SeedDAO;
import com.eep.datarepository.impl.ShrubDAO;
import com.eep.datarepository.impl.TreesDAO;

/**
 *
 * @author zhongzhu
 */
public class ServiceFactory {

    public static IInventoryService createInventoryService() {
        return new InventoryService(new TreesDAO(), new ShrubDAO(), new SeedDAO(),
                new CultureBoxDAO(), new GenomicDAO(), new ProcessingDAO(),
                new ReferenceMaterialDAO());
    }

    public static IOrderService createOrderService() {
        return new OrderService();
    }
}
