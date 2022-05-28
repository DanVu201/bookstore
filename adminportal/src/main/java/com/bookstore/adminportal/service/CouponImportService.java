package com.bookstore.adminportal.service;

import com.bookstore.adminportal.domain.CouponImport;
import org.springframework.stereotype.Service;

@Service
public interface CouponImportService {

    void save(CouponImport couponImport);
}
