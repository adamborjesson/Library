package com.example.library.service;


import com.example.library.model.Category;
import com.example.library.repository.CategoryRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadDataService implements CommandLineRunner {

    private final CategoryRepository categoryRepository;


    @Autowired
    public LoadDataService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws IOException {

        if(categoryRepository.findAll().isEmpty()) {
            List<Category> categorys = new ArrayList<>();

            categorys.add(new Category("Fantasy"));
            categorys.add(new Category("Romance"));
            categorys.add(new Category("Adventure"));
            categorys.add(new Category("Science Fiction"));

            categoryRepository.saveAll(categorys);
        }

    }

}

