package com.cx330.orders.service.impl;

import com.cx330.entity.SaleOrder;
import com.cx330.entity.SaleOrderDetail;
import com.cx330.orders.mapper.SaleOrderDao;
import com.cx330.orders.service.SaleOrderDetailService;
import com.cx330.orders.service.SaleOrderService;
import com.cx330.utility.HashMapBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cx330
 * 思路：创建商品详细订单时，需要先查询库存信息，如果满足订单创建则扣减库存，创建订单，否则不创建订单。
 *      如果需要增加商品数量，则需要先进行判断库存是否足够，减少库存，然后再修改订单。
 *      如果需要减少商品数量，则需要进行原先库存的修改，然后再修改订单。*
 * 这里采用HashMap存储订单信息
 *
 * */


@Service
@Transactional
public class SaleOrderImpl implements SaleOrderService {
    @Autowired
    private SaleOrderDao saleOrderDao;
    @Autowired
    private SaleOrderDetailService saleOrderDetailService;

    // TODO: 缓存订单信息
    @Override
    public int createOrder(SaleOrder order, SaleOrderDetail detail) {
        // TODO: 获取库存信息

        // TODO: 对比是否满足出货信息,如果足够返回1，否则返回0


        // 保存订单信息
        HashMapBuffer.getInstance().addCacheData( "order" + String.valueOf(order.getOrderId()), order);
        HashMapBuffer.getInstance().addCacheData( "detail" + String.valueOf(order.getOrderId()), detail);
        return 0;
    }
    // TODO: 确认订单
    @Override
    public int confirmOrder(Integer orderId) {
        // TODO: 扣除库存

        // TODO: 数据库创建订单
        int order = saleOrderDao.createOrder((SaleOrder) HashMapBuffer.getInstance().getCacheData("order" + String.valueOf(orderId)));
        int addSaleOrderDetail = saleOrderDetailService.addSaleOrderDetail((SaleOrderDetail) HashMapBuffer.getInstance().getCacheData("detail" + String.valueOf(orderId)));
        // TODO: 清除缓存
        HashMapBuffer.getInstance().removeCacheData("order" + String.valueOf(orderId));
        HashMapBuffer.getInstance().removeCacheData("detail" + String.valueOf(orderId));
        if (order == 0 || addSaleOrderDetail == 0){
            throw new RuntimeException("订单创建失败");
        }
        return 1;
    }
    // TODO: 取消订单
    @Override
    public int cancelOrder(Integer orderId) {
        HashMapBuffer.getInstance().removeCacheData("order" + String.valueOf(orderId));
        HashMapBuffer.getInstance().removeCacheData("detail" + String.valueOf(orderId));
        return 1;
    }

    @Override
    public int updateOrder(SaleOrder order) {
        return saleOrderDao.updateOrder(order);
    }

    @Override
    public int deleteOrder(Integer orderId) {
        return saleOrderDao.deleteOrder(orderId);
    }

    @Override
    public SaleOrder getOrderById(Integer orderId) {
        return saleOrderDao.getOrderById(orderId);
    }

    @Override
    public List<SaleOrder> getAllOrders() {
        return saleOrderDao.getAllOrders();
    }

    @Override
    public List<SaleOrder> getOrderByCustomerId(String customerId) {
        return saleOrderDao.getOrderByCustomerId(customerId);
    }

    @Override
    public List<SaleOrder> getOrderByStatus(String orderStatus) {
        return saleOrderDao.getOrderByStatus(orderStatus);
    }
}
