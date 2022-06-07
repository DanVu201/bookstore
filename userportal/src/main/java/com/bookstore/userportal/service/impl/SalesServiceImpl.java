package com.bookstore.userportal.service.impl;

import com.bookstore.userportal.domain.Book;
import com.bookstore.userportal.domain.Sales;
import com.bookstore.userportal.dto.SalesBookDTO;
import com.bookstore.userportal.repository.BookRepository;
import com.bookstore.userportal.repository.SalesRepository;
import com.bookstore.userportal.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private BookRepository bookRepository;


    @Override 
    public List<SalesBookDTO> listSalesBook(int month, int year) {
        List<SalesBookDTO> listBestSeller = new ArrayList<>();
        if(month != 0 && year == 0){
            int year2 = LocalDate.now().getYear();
            List<int[]> listMap = salesRepository.listBookByMonth(month, year2);
            for (int i = 0; i < listMap.size(); i++) {
                Optional<Book> book = bookRepository.findById(Long.valueOf(listMap.get(i)[0]));
                SalesBookDTO salesBookDTO = SalesBookDTO.builder()
                        .book(book)
                        .quantity(listMap.get(i)[1]).build();
                listBestSeller.add(salesBookDTO);
            }
        }
        if (month==0 && year!=0){
            List<int[]> listMap = salesRepository.listBookByYear(year);
            for (int i = 0; i < listMap.size(); i++) {
                Optional<Book> book = bookRepository.findById(Long.valueOf(listMap.get(i)[0]));
                SalesBookDTO salesBookDTO = SalesBookDTO.builder()
                        .book(book)
                        .quantity(listMap.get(i)[1]).build();
                listBestSeller.add(salesBookDTO);
            }
        }
        if (month!=0 && year!=0){
            List<int[]> listMap = salesRepository.listBookByMonth(month, year);
            for (int i = 0; i < listMap.size(); i++) {
                Optional<Book> book = bookRepository.findById(Long.valueOf(listMap.get(i)[0]));
                SalesBookDTO salesBookDTO = SalesBookDTO.builder()
                        .book(book)
                        .quantity(listMap.get(i)[1]).build();
                listBestSeller.add(salesBookDTO);
            }
        }
        return listBestSeller;
    }

    @Override
    public void insertOrUpdateSalesBook(Long bookId, int quantity, Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        Sales sale = salesRepository.findToInsertOrUpdate(bookId, month, year);
        if (sale != null){
            sale.setQuantity(sale.getQuantity() + quantity);
            salesRepository.save(sale);
        }
        else {
            Sales newSales = Sales.builder()
                    .bookId(bookId)
                    .month(month)
                    .year(year)
                    .quantity(quantity).build();
            salesRepository.save(newSales);
        }
    }
}
