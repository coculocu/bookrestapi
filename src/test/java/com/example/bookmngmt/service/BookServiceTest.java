package com.example.bookmngmt.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.bookmngmt.TestData;
import com.example.bookmngmt.exception.ResourceAlreadyExistsException;
import com.example.bookmngmt.exception.ResourceNotFoundException;
import com.example.bookmngmt.model.Book;
import com.example.bookmngmt.repository.BookGatewayRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookGatewayRepository booksRepository;

    @InjectMocks
    private BookService bookService;

    @Nested
    class GetAllTests {

        @Test
        void getAllBooksWhenNoBooks() {
            // Arrange
            Mockito.when(booksRepository.findAll()).thenReturn(new ArrayList<>());

            // Act
            List<Book> result = bookService.getAllBooks();

            // Assert
            assertEquals(0, result.size());
        }

        @Test
        void getAllBooksWhenBooksExist() {
            // Arrange
            var books = TestData.getBooks();
            Mockito.when(booksRepository.findAll()).thenReturn(books);

            // Act
            List<Book> result = bookService.getAllBooks();

            // Assert
            assertFalse(result.isEmpty());
            assertEquals(books.size(), result.size());
        }
    }

    @Nested
    class AddBookTests {

        @Test
        void addBookValid() {
            // Arrange
            Book book = TestData.getBook();
            Mockito.when(booksRepository.save(Mockito.any(Book.class))).thenReturn(book);

            // Act
            Book result = bookService.addBook(book);

            // Assert
            assertEquals(book.getId(), result.getId());
            assertEquals(book.getTitle(), result.getTitle());
            assertEquals(book.getAuthor(), result.getAuthor());
            assertEquals(book.getIsbn(), result.getIsbn());
            assertEquals(book.getQuantity(), result.getQuantity());
        }

        @Test
        void addBookAlreadyExists() {
            // Arrange
            Book book = TestData.getBook();
            Mockito.when(booksRepository.findBookByTitle(Mockito.anyString())).thenReturn(Optional.of(book));

            // Act and Assert
            assertThrows(ResourceAlreadyExistsException.class, () -> bookService.addBook(book));
        }
    }

    @Nested
    class DeleteBookTests {

        @Test
        @DisplayName("When book ID is not found, should throw an exception")
        void deleteBookNotFound() {
            Mockito.when(booksRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
            assertThrows(ResourceNotFoundException.class, () -> bookService.deleteBook(1));
        }

        @Test
        @DisplayName("When book ID is valid, should delete the book")
        void deleteBookValid() {
            var book = TestData.getBook();
            Mockito.when(booksRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(book));
            assertDoesNotThrow(() -> bookService.deleteBook(book.getId()));
        }

    }
}