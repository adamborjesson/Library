package com.example.library.dto;

import com.example.library.model.book.Book;
import lombok.Value;

import java.util.List;

@Value
public class CategoryDTO {
    Long id;
    String name;
    List<BookDTO> books;
}
