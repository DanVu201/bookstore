package com.bookstore.userportal.service;


import com.bookstore.userportal.dto.SalesBookDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface SalesService {

    List<SalesBookDTO> listSalesBook(int month, int year);

    void insertOrUpdateSalesBook(Long bookId, int quantity, Date date);
}
