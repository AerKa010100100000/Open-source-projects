package com.cx330.orders.service;

import com.cx330.entity.SaleOrder;
import com.cx330.entity.SaleOrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleOrderService {
    // 创建订单
    int createOrder(SaleOrder order, SaleOrderDetail detail);
    // 确认订单接口
    int confirmOrder(Integer orderId);
    // 取消订单接口
    int cancelOrder(Integer orderId);

    // 修改订单
    int updateOrder(SaleOrder order);
    // 删除订单
    int deleteOrder(Integer orderId);
    // 根据id查询订单
    SaleOrder getOrderById(Integer orderId);
    // 查询所有订单
    List<SaleOrder> getAllOrders();
    // 根据客户编号查询订单
    List<SaleOrder> getOrderByCustomerId(String customerId);
    // 根据订单状态查询订单
    List<SaleOrder> getOrderByStatus(String orderStatus);
}
