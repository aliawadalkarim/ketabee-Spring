package com.alkma.ketabee.book;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    public BookResponse createBook(BookRequest bookRequest) {
        Book book = bookRepository.save(bookMapper.toBook(bookRequest));
        return bookMapper.toBookResponse(book);
    }
}
