package com.cx330.orders.mapper;


import com.cx330.entity.SaleOrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SaleOrderDetailDao {
    // 添加订单详情
    int addSaleOrderDetail(SaleOrderDetail saleOrderDetail);
    // 修改订单详情
    int updateSaleOrderDetail(SaleOrderDetail saleOrderDetail);
    // 删除订单详情
    int deleteSaleOrderDetail(Integer detailId);
    // 根据订单编号查询订单详情
    SaleOrderDetail getSaleOrderDetailByOrderId(Integer orderId);
    // 查询订单详情列表
    List<SaleOrderDetail> getSaleOrderDetailList();

}