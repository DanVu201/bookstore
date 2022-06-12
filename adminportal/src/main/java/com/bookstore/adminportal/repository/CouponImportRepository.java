package com.bookstore.adminportal.repository;

import com.bookstore.adminportal.domain.CouponImport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponImportRepository extends CrudRepository<CouponImport, Long> {

    @Query(value = "SELECT SUM(c.totalPrice) FROM CouponImport c WHERE MONTH(c.dateImport) = :month AND YEAR(c.dateImport) = :year")
    Integer getTotalPriceCouponImport(Integer month, Integer year);

}
