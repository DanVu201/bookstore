package com.bookstore.adminportal.repository;

import com.bookstore.adminportal.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BookRepository extends CrudRepository<Book, Long> {

    @Query(value = "SELECT b FROM Book b")
    List<Book> findAll();




}
