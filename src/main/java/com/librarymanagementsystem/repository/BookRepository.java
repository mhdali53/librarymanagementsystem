package com.librarymanagementsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagementsystem.dto.Book;

public interface BookRepository extends JpaRepository<Book,Long>{

	List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByIsAvailable(boolean isAvailable);
    Optional<Book> findByIsbn(String isbn);
    void deleteByIsbn(String isbn);
}
