package com.librarymanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.librarymanagementsystem.dto.Book;

public interface BookRepository extends JpaRepository<Book,Long>{

	List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByIsAvailable(boolean isAvailable);
}
