/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.businessservice;

import com.eep.businessservice.dto.InventoryItemInfo;
import java.util.List;

/**
 *
 * @author zhongzhu
 */
public interface IInventoryService {

    void addTree(InventoryItemInfo info);

    void addShrub(InventoryItemInfo info);

    void addSeed(InventoryItemInfo info);

    List<InventoryItemInfo> getAllTrees();

    List<InventoryItemInfo> getAllShrubs();

    List<InventoryItemInfo> getAllSeeds();
}
