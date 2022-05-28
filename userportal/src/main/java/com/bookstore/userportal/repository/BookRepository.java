package com.bookstore.userportal.repository;


import java.util.List;

import com.bookstore.userportal.domain.Book;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, Long>{
	
List<Book> findByCategory(String category);
	
	List<Book> findByTitleContaining(String title);

}
