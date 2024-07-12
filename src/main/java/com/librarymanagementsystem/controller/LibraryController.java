package com.librarymanagementsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.librarymanagementsystem.dto.Book;
import com.librarymanagementsystem.service.LibraryService;

import java.util.List;
	
	@RestController
	@RequestMapping("/api/library")
	public class LibraryController {
	    @Autowired
	    private LibraryService libraryService;

	    @PostMapping("/books")
	    public ResponseEntity<Book> createBook(@RequestBody Book book) {
	    	System.out.println("Received book: " + book);
	       Book savedBook = libraryService.addBook(book);
	       System.out.println("Saved book: " + savedBook);
	        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
	    }

	    @DeleteMapping("/books/{id}")
	    public ResponseEntity<String> removeBook(@PathVariable Long id) {
	        libraryService.removeBook(id);
	        return new ResponseEntity<String>("Book deleted successfully!.", HttpStatus.OK);
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


