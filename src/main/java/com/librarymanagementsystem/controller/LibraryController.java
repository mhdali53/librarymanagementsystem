package com.librarymanagementsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.librarymanagementsystem.dto.Book;
import com.librarymanagementsystem.service.LibraryService;

import java.util.List;
import java.util.Optional;
	
	@RestController
	@RequestMapping("/api/library")
	public class LibraryController {
	    @Autowired
	    private LibraryService libraryService;

	    @PostMapping("/books")
	    public ResponseEntity<?> createBook(@RequestBody Book book) {
	    	Optional<Book> existingBook = libraryService.findBookByISBN(book.getIsbn());
	        if (existingBook.isPresent()) {
	           // throw new RuntimeException("Book with ISBN " + book.getIsbn() + " already exists.");
	        	return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Book with ISBN " + book.getIsbn() + " already exists.");
	        }
	        Book savedBook =  libraryService.addBook(book);
	        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
	    }

	    @DeleteMapping("/books/{isbn}")
	    public ResponseEntity<String> removeBook(@PathVariable String isbn) {
	        
	    	try {
	            Optional<Book> optionalBook = libraryService.findBookByISBN(isbn);

	            if (optionalBook.isPresent()) {
	                libraryService.removeBook(isbn);
	                return ResponseEntity.ok("Book with ISBN " + isbn + " deleted successfully.");
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                     .body("No book found with ISBN " + isbn);
	            }
	        } catch (Exception e) {
	            
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body("Internal server error: " + e.getMessage());
	        }
	    }
	    

	    @GetMapping("/books/title/{title}")
	    public ResponseEntity<List<Book>> findBookByTitle(@PathVariable String title) {
	       List<Book> booksWithTitle =  libraryService.findBookByTitle(title);
	        return new ResponseEntity<>(booksWithTitle, HttpStatus.OK);
	        
	        
	    }

	    @GetMapping("/books/author/{author}")
	    public ResponseEntity<List<Book>> findBookByAuthor(@PathVariable String author) {
	    	List<Book> booksByAuthor =  libraryService.findBookByAuthor(author);
	        return new ResponseEntity<>(booksByAuthor, HttpStatus.OK);
	    }

	    @GetMapping("/books")
	    public ResponseEntity<List<Book>> listAllBooks() {
	    	List<Book> books = libraryService.listAllBooks();
	        return new ResponseEntity<>(books, HttpStatus.OK);
	    }

	    @GetMapping("/books/available")
	    public List<Book> listAvailableBooks() {
	        return libraryService.listAvailableBooks();
	    }
	}


