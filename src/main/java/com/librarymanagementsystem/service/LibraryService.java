package com.librarymanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public boolean removeBook(String isbn) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        if (optionalBook.isPresent()) {
            bookRepository.deleteByIsbn(isbn);
            return true;
        }
        return false;
    }
    
    public List<Book> findBookByTitle(String title) {
        return bookRepository.findByTitleIgnoreCase(title);
    }

    public List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthorIgnoreCase(author);
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> listAvailableBooks() {
        return bookRepository.findByIsAvailable(true);
    }
    
    @Transactional(readOnly = true)
    public Optional<Book> findBookByISBN(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
}
