package com.example.library.dto;

import java.util.List;

import com.example.library.model.Category;
import com.example.library.model.book.EBook;
import lombok.Value;

@Value
public class BookDTO {
    Long id;
    String name;
    String category;
    Long copies;
    EBook state;
}
