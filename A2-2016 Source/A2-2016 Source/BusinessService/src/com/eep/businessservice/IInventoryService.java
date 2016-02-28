/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.businessservice;

import com.eep.businessservice.dto.OrderItemInfo;
import java.util.List;

/**
 *
 * @author zhongzhu
 */
public interface IInventoryService {

    void addTree(OrderItemInfo info);

    void addShrub(OrderItemInfo info);

    void addSeed(OrderItemInfo info);
    
    void addCultureBox(OrderItemInfo info);
    
    void addGenomic(OrderItemInfo info);
    
    void addProcessing(OrderItemInfo info);
    
    void addReferenceMaterial(OrderItemInfo info);

    void deleteTree(String productCode);

    void deleteShrub(String productCode);

    void deleteSeed(String productCode);
    
    void deleteCultureBox(String productCode);
    
    void deleteGenomic(String productCode);
    
    void deleteProcessing(String productCode);
    
    void deleteReferenceMaterial(String productCode);

    OrderItemInfo decrementTree(String productCode);

    OrderItemInfo decrementShrub(String productCode);

    OrderItemInfo decrementSeed(String productCode);
    
    OrderItemInfo decrementCultureBox(String productCode);
    
    OrderItemInfo decrementGenomic(String productCode);
    
    OrderItemInfo decrementProcessing(String productCode);
    
    OrderItemInfo decrementReferenceMaterial(String productCode);

    List<OrderItemInfo> getAllTrees();

    List<OrderItemInfo> getAllShrubs();

    List<OrderItemInfo> getAllSeeds();
    
    List<OrderItemInfo> getAllCultureBox();
    
    List<OrderItemInfo> getAllGenomic();
    
    List<OrderItemInfo> getAllProcessing();
    
    List<OrderItemInfo> getAllReferenceMaterial();
}
