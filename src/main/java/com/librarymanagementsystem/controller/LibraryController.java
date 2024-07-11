package com.librarymanagementsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
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

	    @PostMapping
	    public ResponseEntity<Book> createBook(@RequestBody Book book) {
	    
	        return ResponseEntity.ok().body(this.libraryService.addBook(book));
	    }

	    @DeleteMapping("/books/{id}")
	    public void removeBook(@PathVariable Long id) {
	        libraryService.removeBook(id);
	    }

	    @GetMapping("/books/title/{title}")
	    public List<Book> findBookByTitle(@PathVariable String title) {
	        return libraryService.findBookByTitle(title);
	    }

	    @GetMapping("/books/author/{author}")
	    public List<Book> findBookByAuthor(@PathVariable String author) {
	        return libraryService.findBookByAuthor(author);
	    }

	    @GetMapping("/books")
	    public List<Book> listAllBooks() {
	        return libraryService.listAllBooks();
	    }

	    @GetMapping("/books/available")
	    public List<Book> listAvailableBooks() {
	        return libraryService.listAvailableBooks();
	    }
	}


