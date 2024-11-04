package com.example.library.service;


import java.util.List;
import java.util.Optional;

import com.example.library.dto.BookDTO;
import com.example.library.dto.BookRegistrationDTO;
import com.example.library.dto.RestockBookDTO;
import com.example.library.model.Category;
import com.example.library.model.book.EBook;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.library.model.book.Book;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public BookDTO postBook(BookRegistrationDTO bookRegistrationDTO) throws Exception {
        if(categoryRepository.findById(bookRegistrationDTO.getCategoryId()).isEmpty()) {
            throw new Exception();
        }
        Category category = categoryRepository.findById(bookRegistrationDTO.getCategoryId()).get();
        Book book = bookRepository.save(new Book(bookRegistrationDTO, category));
        category.getBooks().add(book);
        categoryRepository.save(category);
        return book.getFullDto();
    }

    public BookDTO getBook(Long id) {

        return bookRepository.findById(id).get().getFullDto();
    }

    public BookDTO sellBook(Long id) throws Exception {
        Book book = bookRepository.findById(id).get();

        if(book.getState().equals(EBook.OutOfStock)) {
            throw new Exception();
        }

            Long copies = book.getCopies() - 1;
            book.setCopies(copies);

        if(book.getCopies() == 0) {
            book.setState(EBook.OutOfStock);
        }
        return bookRepository.save(book).getFullDto();
    }

    public BookDTO restock(RestockBookDTO restockBookDTO) throws Exception {
        if(bookRepository.findById(restockBookDTO.getId()).isEmpty()) {
            throw new Exception();
        }
        Book book = bookRepository.findById(restockBookDTO.getId()).get();
        Long copies = book.getCopies() + restockBookDTO.getCopies();
        book.setCopies(copies);

        if(book.getState().equals(EBook.OutOfStock)) {
            book.setState(EBook.InStock);
        }

        return bookRepository.save(book).getFullDto();
    }

    public BookDTO getBookByName(String name) {
        System.out.println(1);
        Optional<Book> book = bookRepository.findByName(name);
        System.out.println(2);
        return book.get().getFullDto();
    }
}
