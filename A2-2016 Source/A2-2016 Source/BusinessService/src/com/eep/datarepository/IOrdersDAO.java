/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository;

import com.eep.datarepository.dto.OrderDTO;
import java.util.List;

/**
 *
 * @author zhongzhu
 */
public interface IOrdersDAO {

    List<OrderDTO> queryAllOrders();

    OrderDTO queryOrdersByOrderID(Long orderID);

    void insertOrder(OrderDTO order);

    void updateOrder(OrderDTO order);
}
