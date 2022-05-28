package com.bookstore.adminportal.service.impl;

import com.bookstore.adminportal.domain.CouponImport;
import com.bookstore.adminportal.repository.CouponImportRepository;
import com.bookstore.adminportal.service.CouponImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponImportServiceImpl implements CouponImportService {

    @Autowired
    private CouponImportRepository couponImportRepository;

    @Override
    public void save(CouponImport couponImport) {
        couponImportRepository.save(couponImport);
    }
}
