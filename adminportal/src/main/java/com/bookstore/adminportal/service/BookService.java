package com.bookstore.adminportal.service;

import com.bookstore.adminportal.domain.Book;

import java.io.File;
import java.io.IOException;
import java.util.List;


public interface BookService {
	
	Book save(Book book);

	List<Book> findAll();
	
	Book findById(Long id);

	void deleteById(long parseLong);

	String scanQR(File qrCodeimage) throws IOException;
}
