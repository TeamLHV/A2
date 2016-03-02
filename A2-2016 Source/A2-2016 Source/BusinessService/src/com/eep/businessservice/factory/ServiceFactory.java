/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.businessservice.factory;

import com.eep.businessservice.IInventoryService;
import com.eep.businessservice.IOrderService;
import com.eep.businessservice.IUserService;
import com.eep.businessservice.dto.UserInfo;
import com.eep.businessservice.impl.InventoryService;
import com.eep.businessservice.impl.OrderService;
import com.eep.businessservice.impl.UserService;
import com.eep.datarepository.impl.CultureBoxDAO;
import com.eep.datarepository.impl.GenomicDAO;
import com.eep.datarepository.impl.OrdersDAO;
import com.eep.datarepository.impl.ProcessingDAO;
import com.eep.datarepository.impl.ReferenceMaterialDAO;
import com.eep.datarepository.impl.SeedDAO;
import com.eep.datarepository.impl.ShrubDAO;
import com.eep.datarepository.impl.TreesDAO;
import com.eep.datarepository.impl.UserDAO;

/**
 *
 * @author zhongzhu
 */
public class ServiceFactory {

    public static IInventoryService createInventoryService(UserInfo userInfo) {
        return new InventoryService(new TreesDAO(userInfo), new ShrubDAO(userInfo), new SeedDAO(userInfo),
                new CultureBoxDAO(userInfo), new GenomicDAO(userInfo), new ProcessingDAO(userInfo),
                new ReferenceMaterialDAO(userInfo));
    }

    public static IOrderService createOrderService(UserInfo userInfo) {
        return new OrderService(new OrdersDAO(userInfo));
    }
    
    public static IUserService createUserService(UserInfo userInfo) {
        return new UserService(new UserDAO(userInfo));
    }
}
