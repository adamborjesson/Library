package com.example.library.model;

import com.example.library.dto.BookDTO;
import com.example.library.dto.CategoryDTO;
import com.example.library.model.book.Book;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Book> books;

    public Category(String name) {

        this.name = name;
    }

    public CategoryDTO getFullDto() {
        List<BookDTO> bookDTOs = this.books.stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getName(),
                        book.getCategory() != null ? book.getCategory().getName() : null, // handling potential null
                        book.getCopies(),
                        book.getState()))
                .collect(Collectors.toList());
        return new CategoryDTO(
                this.getId(),
                this.getName(),
                bookDTOs);
    }
}