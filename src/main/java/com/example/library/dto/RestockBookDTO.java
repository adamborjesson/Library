package com.example.library.dto;

import com.example.library.model.book.EBook;
import lombok.Value;

@Value
public class RestockBookDTO {
    Long id;
    Long copies;
}
