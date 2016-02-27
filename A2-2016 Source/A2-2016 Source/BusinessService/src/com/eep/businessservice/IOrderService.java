/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.businessservice;

import com.eep.businessservice.dto.OrderItemInfo;
import com.eep.businessservice.dto.OrderInfo;
import java.util.List;

/**
 *
 * @author zhongzhu
 */
public interface IOrderService {

    void createNewOrder(OrderInfo orderInfo);

    void addItemToOrder(OrderInfo orderInfo, OrderItemInfo item);

    List<OrderInfo> getAllOrders();

    OrderInfo getOrderByID(Long orderID);

    List<OrderItemInfo> getAllItems(OrderInfo orderInfo);
    
    void shipOrder(OrderInfo orderInfo);

}
