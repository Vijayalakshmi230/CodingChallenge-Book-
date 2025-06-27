package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.repo.BookRepository;

@Service
public class BookService {
    @Autowired private BookRepository repo;

    public List<Book> getAll() { return repo.findAll(); }

    public Book getByIsbn(String isbn) {
        return repo.findById(isbn).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book save(Book book) { return repo.save(book); }

    public Book update(String isbn, Book updatedBook) {
        Book book = getByIsbn(isbn);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPublicationYear(updatedBook.getPublicationYear());
        return repo.save(book);
    }

    public void delete(String isbn) { repo.deleteById(isbn); }
}

