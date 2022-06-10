package com.bookstore.adminportal.repository;


import com.bookstore.adminportal.domain.Sales;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SalesRepository extends CrudRepository<Sales, Long> {
    @Query(value = "SELECT bo.id, s.quantity FROM book bo " +
            "LEFT JOIN sales s on bo.id = s.book_id " +
            "WHERE s.month = :month AND s.year = :year " +
            "ORDER BY s.quantity desc",nativeQuery = true)
    List<int[]> listBookByMonth(int month, int year);


    @Query(value = "SELECT bo.id, SUM(s.quantity) AS q FROM book bo " +
            "LEFT JOIN sales s on bo.id = s.book_id " +
            "WHERE s.year = ?1 " +
            "GROUP BY s.book_id " +
            "ORDER BY q desc",nativeQuery = true)
    List<int[]> listBookByYear(int year);

    @Query("SELECT s FROM Sales s WHERE s.bookId = ?1 AND s.month = ?2 AND s.year = ?3")
    Sales findToInsertOrUpdate(Long bookId, int month, int year);

    @Query(value = "SELECT s.month, SUM(s.quantity) as quantity FROM sales s WHERE s.year = ?1 GROUP BY s.month ORDER BY s.month asc", nativeQuery = true)
    List<int[]> listSales(int year);

    @Query(value = "SELECT SUM(s.quantity), s.category as quantity FROM sales s WHERE s.year = ?1 GROUP BY s.category ", nativeQuery = true)
    List<String[]> listAllSales(int year);
}
