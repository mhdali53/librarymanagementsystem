package com.librarymanagementsystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.librarymanagementsystem.dto.Book;
import com.librarymanagementsystem.repository.BookRepository;

class LibraryServiceTest {

	@Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LibraryService libraryService;
    
    @BeforeEach
    void setup() {
    	
    	MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testAddBook() {
        Book book = new Book(null, "Title", "Author", "ISBN1", "Genre", 2023, "Dept", true);
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        Book savedBook = libraryService.addBook(book);
        assertEquals(book.getTitle(), savedBook.getTitle());
    }
    
    @Test
    public void testRemoveBook() {
    	Mockito.doNothing().when(bookRepository).deleteById(1L);
        libraryService.removeBook(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }

}
