/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.businessservice.impl;

import com.eep.businessservice.IOrderService;
import com.eep.businessservice.dto.OrderItemInfo;
import com.eep.businessservice.dto.OrderInfo;
import com.eep.businessservice.exception.CreateTableException;
import com.eep.businessservice.exception.DropTableException;
import com.eep.datarepository.IOrdersDAO;
import com.eep.datarepository.dto.OrderDTO;
import com.eep.datarepository.dto.OrderTableItemDTO;
import com.eep.datarepository.impl.OrdersDAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        orderInfo.setShipped(false);
        
        int TheHour = rightNow.get(Calendar.HOUR_OF_DAY);
        int TheMinute = rightNow.get(Calendar.MINUTE);
        int TheSecond = rightNow.get(Calendar.SECOND);
        int TheDay = rightNow.get(Calendar.DAY_OF_WEEK);
        int TheMonth = rightNow.get(Calendar.MONTH);
        int TheYear = rightNow.get(Calendar.YEAR);
        String dateTimeStamp = TheMonth + "/" + TheDay + "/" + TheYear + " "
                + TheHour + ":" + TheMinute + ":" + TheSecond;
        orderInfo.setOrderDate(dateTimeStamp);
        
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
        
        OrderDTO dto = convertInfoToDTO(orderInfo);
        ordersDAO.insertOrder(dto);
    }
    
    private OrderDTO convertInfoToDTO(OrderInfo orderInfo) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderID(orderInfo.getOrderID());
        dto.setFirstName(orderInfo.getFirstName());
        dto.setLastName((orderInfo.getLastName()));
        dto.setAddress(orderInfo.getAddress());
        dto.setOrderDate(orderInfo.getOrderDate());
        dto.setOrderTable(orderInfo.getOrderTableName());
        dto.setPhone(orderInfo.getPhone());
        dto.setShipped(orderInfo.getShipped());
        dto.setTotalCost(orderInfo.getTotalCost());
        return dto;
    }
    
    @Override
    public void addItemToOrder(OrderInfo orderInfo, OrderItemInfo item) {
        OrderTableItemDTO dto = new OrderTableItemDTO();
        dto.setDescription(item.getDescription());
        dto.setPrice(item.getPrice());
        dto.setProductCode(item.getProductCode());
        ordersDAO.insertIntoOrderTable(orderInfo.getOrderTableName(), dto);
    }
    
    @Override
    public List<OrderInfo> getAllOrders() {
        List<OrderInfo> result = new ArrayList<>();
        OrderInfo temp;
        
        List<OrderDTO> dtos = ordersDAO.queryAllOrders();
        for (OrderDTO dto : dtos) {
            temp = new OrderInfo();
            temp.setAddress(dto.getAddress());
            temp.setFirstName(dto.getFirstName());
            temp.setLastName(dto.getLastName());
            temp.setOrderDate(dto.getOrderDate());
            temp.setOrderID(dto.getOrderID());
            temp.setOrderTableName(dto.getOrderTable());
            temp.setPhone(dto.getPhone());
            temp.setShipped(dto.getShipped());
            temp.setTotalCost(dto.getTotalCost());
            result.add(temp);
        }
        
        return result;
    }
    
    @Override
    public List<OrderItemInfo> getAllItems(OrderInfo orderInfo) {
        List<OrderItemInfo> result = new ArrayList<>();
        OrderItemInfo temp;
        List<OrderTableItemDTO> dtos = ordersDAO.queryAllOrderItems(orderInfo.getOrderTableName());
        
        for (OrderTableItemDTO dto : dtos) {
            temp = new OrderItemInfo();
            temp.setItemID(dto.getID());
            temp.setProductCode(dto.getProductCode());
            temp.setDescription(dto.getDescription());
            temp.setPrice(dto.getPrice());
            result.add(temp);
        }
        
        return result;
    }
    
    @Override
    public OrderInfo getOrderByID(Long orderID) {
        OrderDTO dto = ordersDAO.queryOrderByOrderID(orderID);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderID(dto.getOrderID());
        orderInfo.setFirstName(dto.getFirstName());
        orderInfo.setLastName(dto.getLastName());
        orderInfo.setAddress(dto.getAddress());
        orderInfo.setOrderDate(dto.getOrderDate());
        orderInfo.setOrderTableName(dto.getOrderTable());
        orderInfo.setPhone(dto.getPhone());
        orderInfo.setTotalCost(dto.getTotalCost());
        return orderInfo;
    }
    
    @Override
    public void shipOrder(OrderInfo orderInfo) {
        orderInfo.setShipped(true);
        ordersDAO.updateOrder(convertInfoToDTO(orderInfo));
    }
    
}
