package com.example.demo.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired private BookService service;

    @GetMapping
    public List<Book> getAll() { return service.getAll(); }

    @GetMapping("/{isbn}")
    public Book get(@PathVariable String isbn) { return service.getByIsbn(isbn); }

    @PostMapping
    public Book create(@RequestBody Book book) { return service.save(book); }

    @PutMapping("/{isbn}")
    public Book update(@PathVariable String isbn, @RequestBody Book book) {
        return service.update(isbn, book);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> delete(@PathVariable String isbn) {
        service.delete(isbn);
        return ResponseEntity.ok().build();
    }
}

