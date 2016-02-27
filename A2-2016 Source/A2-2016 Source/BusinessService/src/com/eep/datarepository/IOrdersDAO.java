/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository;

import com.eep.datarepository.dto.OrderDTO;
import com.eep.datarepository.dto.OrderTableItemDTO;
import java.util.List;

/**
 *
 * @author zhongzhu
 */
public interface IOrdersDAO {

    void createOrderTable(String orderTableName);

    void dropOrderTable(String orderTableName);

    void insertIntoOrderTable(String orderTableName, OrderTableItemDTO item);

    List<OrderTableItemDTO> queryAllOrderItems(String orderTableName);

    List<OrderDTO> queryAllOrders();

    OrderDTO queryOrderByOrderID(Long orderID);

    void insertOrder(OrderDTO order);

    void updateOrder(OrderDTO order);
}
