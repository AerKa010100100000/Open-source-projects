package com.cx330.orders.service.impl;

import com.cx330.entity.SaleOrderDetail;
import com.cx330.orders.mapper.SaleOrderDetailDao;
import com.cx330.orders.service.SaleOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class SaleOrderDetailImpl implements SaleOrderDetailService {
    @Autowired
    private SaleOrderDetailDao saleOrderDetailDao;

    @Override
    public int addSaleOrderDetail(SaleOrderDetail saleOrderDetail) {
        return saleOrderDetailDao.addSaleOrderDetail(saleOrderDetail);
    }

    @Override
    public int updateSaleOrderDetail(SaleOrderDetail saleOrderDetail) {
        return 0;
    }

    @Override
    public int deleteSaleOrderDetail(Integer detailId) {
        return saleOrderDetailDao.deleteSaleOrderDetail(detailId);
    }

    @Override
    public SaleOrderDetail getSaleOrderDetailByOrderId(Integer orderId) {
        return saleOrderDetailDao.getSaleOrderDetailByOrderId(orderId);
    }

    @Override
    public List<SaleOrderDetail> getSaleOrderDetailList() {
        return saleOrderDetailDao.getSaleOrderDetailList();
    }
}
