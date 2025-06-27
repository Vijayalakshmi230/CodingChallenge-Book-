package com.example.demo.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Book;
import com.example.demo.repo.BookRepository;

@SpringBootTest
class BookApiApplicationTests {

    @Autowired private BookRepository repo;

    @Test
    void testSaveAndGetBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setTitle("Test");
        book.setAuthor("Author");
        book.setPublicationYear(2024);

        repo.save(book);
        Optional<Book> fetched = repo.findById("1234");

        assertTrue(fetched.isPresent());
        assertEquals("Test", fetched.get().getTitle());
    }
}


