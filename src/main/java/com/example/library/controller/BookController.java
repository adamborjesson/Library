package com.example.library.controller;

import com.example.library.dto.BookRegistrationDTO;
import com.example.library.dto.RestockBookDTO;
import com.example.library.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.example.library.model.book.Book;
import com.example.library.dto.BookDTO;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {

        this.bookService = bookService;
    }



    @GetMapping("/get/all")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok().body(bookService.getAllBooks()
                .stream()
                .map(Book::getFullDto)
                .collect(Collectors.toList()));
    }

    @PostMapping("post/book")
    public ResponseEntity<BookDTO> postBook(@RequestBody BookRegistrationDTO bookRegistrationDTO) {
        try {
            return ResponseEntity.ok().body(bookService.postBook(bookRegistrationDTO));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category could not be found");
        }
    }

    @GetMapping("/get/book/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(bookService.getBook(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book could not be found");
        }
    }

    @GetMapping("sell/book/{id}")
    public ResponseEntity<BookDTO> sellBook(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(bookService.sellBook(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Out Of Stock");
        }
    }

    @PutMapping("/restock")
    public ResponseEntity<BookDTO> restock(@RequestBody RestockBookDTO restockBookDTO) {
        try {
            return ResponseEntity.ok().body(bookService.restock(restockBookDTO));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not be found");
        }
    }

}
