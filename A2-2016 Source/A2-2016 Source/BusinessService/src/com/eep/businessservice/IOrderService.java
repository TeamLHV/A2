/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.businessservice;

import com.eep.businessservice.dto.InventoryItemInfo;
import com.eep.businessservice.dto.OrderInfo;

/**
 *
 * @author zhongzhu
 */
public interface IOrderService {

    void createNewOrder(OrderInfo orderInfo);

    void addItemToOrder(OrderInfo orderInfo, InventoryItemInfo item);

}
