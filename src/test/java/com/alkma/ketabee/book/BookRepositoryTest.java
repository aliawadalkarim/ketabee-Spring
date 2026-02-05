package com.alkma.ketabee.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldCreateAndFindBookById() {
        Book book = Book.builder().title("The Great Gatsby").author("F. Scott Fitzgerald").isbn("9780743273565")
                .price(10.99).description("A story of the American Dream").publisher("Scribner")
                .publicationDate("1925-04-10").category("Fiction").language("English").status("AVAILABLE").build();

        Book savedBook = bookRepository.save(book);
        Optional<Book> foundBook = bookRepository.findById(savedBook.getId());

        assertTrue(foundBook.isPresent());
        assertEquals("The Great Gatsby", foundBook.get().getTitle());
        assertEquals("9780743273565", foundBook.get().getIsbn());
        assertEquals(savedBook, foundBook.get());
    }
}
