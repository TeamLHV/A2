/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.businessservice.impl;

import com.eep.businessservice.IOrderService;
import com.eep.businessservice.dto.InventoryItemInfo;
import com.eep.businessservice.dto.OrderInfo;
import com.eep.businessservice.exception.CreateTableException;
import com.eep.businessservice.exception.DropTableException;
import com.eep.datarepository.IOrdersDAO;
import com.eep.datarepository.dto.OrderDTO;
import com.eep.datarepository.dto.OrderTableItemDTO;
import com.eep.datarepository.impl.OrdersDAO;
import java.util.Calendar;

/**
 *
 * @author zhongzhu
 */
public class OrderService implements IOrderService {

    private final IOrdersDAO ordersDAO;

    public OrderService(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    @Override
    public void createNewOrder(OrderInfo orderInfo) {
        Calendar rightNow = Calendar.getInstance();
        String orderTableName = "order" + String.valueOf(rightNow.getTimeInMillis());
        orderInfo.setOrderTableName(orderTableName);

        try {
            ordersDAO.createOrderTable(orderTableName);
        } catch (RuntimeException e) {
            try {
                ordersDAO.dropOrderTable(orderTableName);
            } catch (RuntimeException e1) {
                throw new DropTableException("Problem deleting unused order table " + orderTableName);
            }

            throw new CreateTableException("Problem creating order table " + orderTableName);
        }

        int TheHour = rightNow.get(rightNow.HOUR_OF_DAY);
        int TheMinute = rightNow.get(rightNow.MINUTE);
        int TheSecond = rightNow.get(rightNow.SECOND);
        int TheDay = rightNow.get(rightNow.DAY_OF_WEEK);
        int TheMonth = rightNow.get(rightNow.MONTH);
        int TheYear = rightNow.get(rightNow.YEAR);
        String dateTimeStamp = TheMonth + "/" + TheDay + "/" + TheYear + " "
                + TheHour + ":" + TheMinute + ":" + TheSecond;

        OrderDTO dto = new OrderDTO();
        dto.setFirstName(orderInfo.getFirstName());
        dto.setLastName((orderInfo.getLastName()));
        dto.setAddress(orderInfo.getAddress());
        dto.setOrderDate(dateTimeStamp);
        dto.setOrderTable(orderInfo.getOrderTableName());
        dto.setPhone(orderInfo.getPhone());
        dto.setShipped(false);
        dto.setTotalCost(orderInfo.getTotalCost());

        ordersDAO.insertOrder(dto);
    }

    @Override
    public void addItemToOrder(OrderInfo orderInfo, InventoryItemInfo item) {
        OrderTableItemDTO dto = new OrderTableItemDTO();
        dto.setDescription(item.getDescription());
        dto.setPrice(item.getPrice());
        dto.setProductCode(item.getProductCode());
        ordersDAO.insertIntoOrderTable(orderInfo.getOrderTableName(), dto);
    }

}
