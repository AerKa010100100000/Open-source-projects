package com.cx330.orders.controller;


import com.cx330.entity.SaleOrder;
import com.cx330.entity.SaleOrderDetail;
import com.cx330.orders.service.SaleOrderService;
import com.cx330.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("saleOrders")
public class SaleOrderController {
    @Autowired
    private SaleOrderService saleOrderService;

    // TODO: 创建订单接口
    @PostMapping("createOrder")
    public Result<String> createOrder(@RequestBody SaleOrder order, @RequestBody SaleOrderDetail detail) {
        int createOrder = saleOrderService.createOrder(order, detail);
        if (createOrder == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }
    // TODO: 确认订单接口
    @GetMapping("confirmOrder")
    public Result<String> confirmOrder(Integer orderId) {
        int confirmOrder = saleOrderService.confirmOrder(orderId);
        if (confirmOrder == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }
    // TODO: 取消订单接口
    @GetMapping("cancelOrder")
    public Result<String> cancelOrder(Integer orderId) {
        int cancelOrder = saleOrderService.cancelOrder(orderId);
        if (cancelOrder == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @PutMapping("updateOrder")
    public Result<String> updateOrder(SaleOrder order) {
        int updateOrder = saleOrderService.updateOrder(order);
        if (updateOrder == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }

    @DeleteMapping("deleteOrder")
    public Result<String> deleteOrder(Integer orderId) {
        int deleteOrder = saleOrderService.deleteOrder(orderId);
        if (deleteOrder == 1)
            return new Result<String>().ok();
        return new Result<String>().error();
    }


    @GetMapping("getOrderById")
    public Result<SaleOrder> getOrderById(Integer orderId) {
        SaleOrder order = saleOrderService.getOrderById(orderId);
        if (order != null)
            return new Result<SaleOrder>().ok().data(order);
        return new Result<SaleOrder>().error();
    }
    @GetMapping("getAllOrders")
    public Result<List<SaleOrder>> getAllOrders() {
        List<SaleOrder> orders = saleOrderService.getAllOrders();
        if (orders != null)
            return new Result<List<SaleOrder>>().ok().data(orders);
        return new Result<List<SaleOrder>>().error();
    }

    @GetMapping("getOrderByCustomerId")
    public Result<List<SaleOrder>> getOrderByCustomerId(String customerId) {
        List<SaleOrder> orders = saleOrderService.getOrderByCustomerId(customerId);
        if (orders != null)
            return new Result<List<SaleOrder>>().ok().data(orders);
        return new Result<List<SaleOrder>>().error();
    }

    @GetMapping("getOrderByStatus")
    public Result<List<SaleOrder>> getOrderByStatus(String orderStatus) {
        List<SaleOrder> orders = saleOrderService.getOrderByStatus(orderStatus);
        if (orders != null)
            return new Result<List<SaleOrder>>().ok().data(orders);
        return new Result<List<SaleOrder>>().error();
    }

}
