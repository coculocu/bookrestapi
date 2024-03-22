package com.example.bookmngmt.service;

import com.example.bookmngmt.exception.ResourceAlreadyExistsException;
import com.example.bookmngmt.exception.ResourceNotFoundException;
import com.example.bookmngmt.model.Book;
import com.example.bookmngmt.repository.BookGatewayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
  
  private final BookGatewayRepository booksRepository;

  public List<Book> getAllBooks() {
    return booksRepository.findAll();
  }

  public Book addBook(Book book) {
    Optional<Book> registeredBook = booksRepository.findBookByTitle(book.getTitle());
    if (registeredBook.isPresent())
        throw new ResourceAlreadyExistsException(Book.class.getSimpleName(), registeredBook.get().getId());
    return booksRepository.save(book);
  }

  public void deleteBook(Integer id) {
    abortIfBookDoesNotExist(id);
    booksRepository.delete(id);
  }

  private Book abortIfBookDoesNotExist(Integer id) {
    return booksRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(Book.class.getSimpleName(), id));
  }
}
