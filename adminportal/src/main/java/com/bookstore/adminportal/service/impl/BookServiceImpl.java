package com.bookstore.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.adminportal.domain.Book;
import com.bookstore.adminportal.repository.BookRepository;
import com.bookstore.adminportal.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;
	
	public Book save(Book book) {
		return bookRepository.save(book);
	}
	
	public List<Book> findAll() {
		return bookRepository.findAllByActiveTrue();
	}
	
	public Book findById(Long id) {
		return bookRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(long parseLong) {
		bookRepository.deleteById(parseLong);
	}
}
