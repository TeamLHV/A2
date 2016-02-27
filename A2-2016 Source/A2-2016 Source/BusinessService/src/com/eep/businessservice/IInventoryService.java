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

    void deleteTree(String productCode);

    void deleteShrub(String productCode);

    void deleteSeed(String productCode);

    OrderItemInfo decrementTree(String productCode);

    OrderItemInfo decrementShrub(String productCode);

    OrderItemInfo decrementSeed(String productCode);

    List<OrderItemInfo> getAllTrees();

    List<OrderItemInfo> getAllShrubs();

    List<OrderItemInfo> getAllSeeds();
}
