package com.example.bookmngmt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmngmt.dto.BookRequest;
import com.example.bookmngmt.dto.BookResponse;
import com.example.bookmngmt.helpers.BookMapper;
import com.example.bookmngmt.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {

  private final BookService bookService;
  private final BookMapper bookMapper;
  
  @Operation(summary = "Get all books")
  @GetMapping
  public ResponseEntity<List<BookResponse>> getAllBooks() {
    var books = bookService.getAllBooks();
    var booksResponse = books.stream().map(bookMapper::toResponse).toList();
    return ResponseEntity.ok(booksResponse);
  }

  @Operation(summary = "Create a book")
  @PostMapping
  public ResponseEntity<BookResponse> createBook(@RequestBody @Valid BookRequest bookRequest) {
    var book = bookMapper.toModel(bookRequest);
    book = bookService.addBook(book);
    var bookResponse = bookMapper.toResponse(book);
    return ResponseEntity.status(HttpStatus.CREATED).body(bookResponse);
  }

  @Operation(summary = "Delete a book")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable("id") Integer id) {
    bookService.deleteBook(id);
    return ResponseEntity.noContent().build();
  }
}
