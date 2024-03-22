package com.example.bookmngmt.helpers;

import com.example.bookmngmt.dto.BookRequest;
import com.example.bookmngmt.dto.BookResponse;
import com.example.bookmngmt.model.Book;
import com.example.bookmngmt.repository.BookEntity;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Book mapper methods for model, entity, request and response
 */
@Component
public class BookMapper {
  
  private final ModelMapper mapper;

  @Autowired
  public BookMapper(ModelMapper mapper) {
      this.mapper = mapper;
  }

  public Book toModel(BookEntity entity) {
      return mapper.map(entity, Book.class);
  }

  public BookEntity toEntity(Book model) {
      return mapper.map(model, BookEntity.class);
  }

  public Book toModel(BookRequest request) {
      return mapper.map(request, Book.class);
  }

  public BookRequest toRequest(Book book) {
      return mapper.map(book, BookRequest.class);
  }

  public BookResponse toResponse(Book book) {
      return mapper.map(book, BookResponse.class);
  }

}
