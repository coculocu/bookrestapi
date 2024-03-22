package com.example.bookmngmt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
  private Integer id;
  private String title;
  private String author;
  private String isbn;
  private Integer quantity;
}
