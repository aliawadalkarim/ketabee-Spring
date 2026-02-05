package com.alkma.ketabee.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    @Test
    void shouldCreateBook() {
        BookRequest request = BookRequest.builder().title("The Great Gatsby").build();
        Book bookEntity = new Book();
        Book savedBook = new Book();
        savedBook.setId(1L);
        BookResponse expectedResponse = BookResponse.builder().id(1L).title("The Great Gatsby").build();

        when(bookMapper.toBook(any())).thenReturn(bookEntity);
        when(bookRepository.save(any())).thenReturn(savedBook);
        when(bookMapper.toBookResponse(any())).thenReturn(expectedResponse);

        BookResponse actualResponse = bookService.createBook(request);

        assertEquals(expectedResponse, actualResponse);
        verify(bookRepository, times(1)).save(any());
    }
}