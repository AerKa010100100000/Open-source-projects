package com.cx330.orders.service;

import com.cx330.entity.SaleOrderDetail;

import java.util.List;

public interface SaleOrderDetailService {

    // 添加订单详情
    int addSaleOrderDetail(SaleOrderDetail saleOrderDetail);
    // 修改订单详情
    int updateSaleOrderDetail(SaleOrderDetail saleOrderDetail);
    // 删除订单详情
    int deleteSaleOrderDetail(Integer id);
    // 根据订单编号查询订单详情
    SaleOrderDetail getSaleOrderDetailByOrderId(Integer orderId);
    // 查询订单详情列表
    List<SaleOrderDetail> getSaleOrderDetailList();
}
