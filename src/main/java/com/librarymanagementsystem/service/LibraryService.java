package com.librarymanagementsystem.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagementsystem.dto.Book;
import com.librarymanagementsystem.repository.BookRepository;

@Service
public class LibraryService {

	@Autowired
    private BookRepository bookRepository;
	
    private static final Logger logger = LoggerFactory.getLogger(LibraryService.class);

	
	
	public Book addBook(Book book) {
		 logger.debug("Adding book: {}", book);
        return bookRepository.save(book);
    }

    public void removeBook(Long id) {
        bookRepository.deleteById(id);
    }
    
    public List<Book> findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> listAvailableBooks() {
        return bookRepository.findByIsAvailable(true);
    }
}
