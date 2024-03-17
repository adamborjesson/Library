package com.example.library.model.book;

import com.example.library.dto.BookDTO;
import com.example.library.dto.BookRegistrationDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long categoryId;
    private Long copies;
    private EBook state;

    public Book(BookRegistrationDTO bookRegistrationDTO) {
        this.name = bookRegistrationDTO.getName();
        this.categoryId = bookRegistrationDTO.getCategoryId();
        this.copies = 10L;
        this.state = EBook.InStock;
    }

    public BookDTO getFullDto() {
        return new BookDTO(
                this.getId(),
                this.getName(),
                this.getCategoryId(),
                this.getCopies(),
                this.getState());
    }
}
