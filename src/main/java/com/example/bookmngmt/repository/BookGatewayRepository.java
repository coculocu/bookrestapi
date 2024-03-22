package com.example.bookmngmt.repository;

import com.example.bookmngmt.model.Book;
import com.example.bookmngmt.helpers.BookMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookGatewayRepository {

  private final BookRepository bookRepository;
  private final BookMapper bookMapper;
  
  public List<Book> findAll() {
    return bookRepository.findAll().stream()
        .map(bookMapper::toModel).toList();
  }

  public Optional<Book> findById(Integer id) {
    Optional<BookEntity> entityOpt = bookRepository.findById(id);
        if (entityOpt.isEmpty())
            return Optional.empty();
        var book = bookMapper.toModel(entityOpt.get());
        return Optional.of(book);
  }

  public Optional<Book> findBookByTitle(String title) {
    Optional<BookEntity> entityOpt = bookRepository.findByTitle(title);
    if (entityOpt.isEmpty())
        return Optional.empty();
    return Optional.of(bookMapper.toModel(entityOpt.get()));
}

  public Book save(Book book) {
    return bookMapper.toModel(bookRepository.save(bookMapper.toEntity(book)));
  }

  public void delete(Integer id) {
    bookRepository.deleteById(id);
  }
}
