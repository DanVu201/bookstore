package com.bookstore.adminportal.service;



import com.bookstore.adminportal.dto.SalesBookDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface SalesService {

    List<SalesBookDTO> listSalesBook(int month, int year);

    void insertOrUpdateSalesBook(Long bookId, int quantity, Date date);

    List<int[]> listSales(Long bookId, int year);
}