package com.bookstore.adminportal.repository;

import com.bookstore.adminportal.domain.CouponImport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponImportRepository extends CrudRepository<CouponImport, Long> {

}
