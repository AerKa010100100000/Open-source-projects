package com.cx330.orders.mapper;

import com.cx330.entity.SaleOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SaleOrderDao {
    // 创建订单
    int createOrder(SaleOrder order);
    // 修改订单
    int updateOrder(SaleOrder order);
    // 删除订单
    int deleteOrder(@Param("orderId") Integer orderId);
    // 根据id查询订单
    SaleOrder getOrderById(@Param("orderId") Integer orderId);
    // 查询所有订单
    List<SaleOrder> getAllOrders();
    // 根据客户编号查询订单
    List<SaleOrder> getOrderByCustomerId(@Param("customerId") String customerId);
    // 根据订单状态查询订单
    List<SaleOrder> getOrderByStatus(@Param("orderStatus") String orderStatus);
}
