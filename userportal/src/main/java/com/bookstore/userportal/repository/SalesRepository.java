package com.bookstore.userportal.repository;

import com.bookstore.userportal.domain.Sales;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


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

    @Query(value = "SELECT s.month, s.quantity FROM Sales s WHERE s.bookId = ?1 AND s.year = ?2 ORDER BY s.month asc ")
    List<int[]> listSales(Long bookId, int year);
}
