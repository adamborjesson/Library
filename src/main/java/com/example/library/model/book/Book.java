package com.example.library.model.book;

import com.example.library.dto.BookDTO;
import com.example.library.dto.BookRegistrationDTO;
import com.example.library.model.Category;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;
    private Long copies;
    private EBook state;

    public Book(BookRegistrationDTO bookRegistrationDTO, Category category) {
        this.name = bookRegistrationDTO.getName();
        this.category = category;
        this.copies = 10L;
        this.state = EBook.InStock;
    }

    public BookDTO getFullDto() {
        return new BookDTO(
                this.getId(),
                this.getName(),
                this.getCategory().getName(),
                this.getCopies(),
                this.getState());
    }
}
