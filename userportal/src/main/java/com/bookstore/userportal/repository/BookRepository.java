package com.bookstore.userportal.repository;


import java.util.List;

import com.bookstore.userportal.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, Long>{

	@Query(value = "SELECT b FROM Book b WHERE b.active = true AND b.category = ?1")
	List<Book> findByCategory(String category);

	@Query(value = "SELECT b FROM Book b WHERE b.active = true AND b.title = ?1")
	List<Book> findByTitleContaining(String title);

	@Query(value = "SELECT b FROM Book b WHERE b.active = true")
	List<Book> findAllByActiveTrue();




}
