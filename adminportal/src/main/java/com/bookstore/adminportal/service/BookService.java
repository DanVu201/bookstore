package com.bookstore.adminportal.service;

import com.bookstore.adminportal.domain.Book;

import java.util.List;


public interface BookService {
	
	Book save(Book book);

	List<Book> findAll();
	
	Book findById(Long id);

	void deleteById(long parseLong);
}
