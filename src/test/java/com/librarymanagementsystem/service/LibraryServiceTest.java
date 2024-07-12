package com.librarymanagementsystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

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
        String isbn = "1234567890";
        Book book = new Book();
        book.setIsbn(isbn);
        when(bookRepository.findByIsbn(isbn)).thenReturn(Optional.of(book));
        doNothing().when(bookRepository).deleteByIsbn(isbn);

        boolean result = libraryService.removeBook(isbn);

        assertTrue(result, "Expected book removal to return true");
        verify(bookRepository, times(1)).findByIsbn(isbn);
        verify(bookRepository, times(1)).deleteByIsbn(isbn);
    }
    
    @Test
    public void testRemoveBookNotFound() {
        String isbn = "1234567890";
        when(bookRepository.findByIsbn(isbn)).thenReturn(Optional.empty());

        boolean result = libraryService.removeBook(isbn);

        assertFalse(result, "Expected book removal to return false");

        verify(bookRepository, times(1)).findByIsbn(isbn);
        verify(bookRepository, never()).deleteByIsbn(anyString());
    }

    @Test
    public void testFindBookByTitle() {
        Book book = new Book(null, "The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", "Fiction", 1925, "Literature", true);
        when(bookRepository.findByTitle(book.getTitle())).thenReturn(Arrays.asList(book));
        List<Book> books = libraryService.findBookByTitle("The Great Gatsby");
        assertEquals(1, books.size());
        assertEquals(book.getTitle(), books.get(0).getTitle());
        verify(bookRepository, times(1)).findByTitle("The Great Gatsby");
    }
    
    @Test
    public void testFindBookByAuthor() {
        Book book = new Book(null, "The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", "Fiction", 1925, "Literature", true);
        when(bookRepository.findByAuthor(book.getAuthor())).thenReturn(Arrays.asList(book));
        List<Book> books = libraryService.findBookByAuthor("F. Scott Fitzgerald");
        assertEquals(1, books.size());
        assertEquals(book.getAuthor(), books.get(0).getAuthor());
        verify(bookRepository, times(1)).findByAuthor("F. Scott Fitzgerald");
    }
    
    @Test
    public void testListAllBooks() {
    	Book book = new Book(null, "The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", "Fiction", 1925, "Literature", true);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book));
        List<Book> books = libraryService.listAllBooks();
        assertEquals(1, books.size());
        assertEquals(book.getTitle(), books.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testListAvailableBooks() {
    	Book book = new Book(null, "The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", "Fiction", 1925, "Literature", true);
        when(bookRepository.findByIsAvailable(true)).thenReturn(Arrays.asList(book));
        List<Book> books = libraryService.listAvailableBooks();
        assertEquals(1, books.size());
        assertEquals(book.isAvailable(), books.get(0).isAvailable());
        verify(bookRepository, times(1)).findByIsAvailable(true);
    }
    
    
}
