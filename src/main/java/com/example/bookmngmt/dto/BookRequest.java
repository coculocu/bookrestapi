package com.example.bookmngmt.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
  @NotEmpty(message = "{required.field}")
  @Size(min = 1, max = 200, message = "{invalid.field}")
  private String title;
  @NotEmpty(message = "{required.field}")
  @Size(min = 1, max = 200, message = "{invalid.field}")
  private String author;
  @NotEmpty(message = "{required.field}")
  @Size(min = 1, max = 20, message = "{invalid.field}")
  private String isbn;
  @NotNull(message = "{required.field}")
  @PositiveOrZero(message = "{invalid.number}")
  private Integer quantity;
}
