package com.alkma.ketabee.book;

import lombok.Builder;

@Builder
public record BookResponse(
    Long id,
    String title,
    String author,
    String isbn,
    double price,
    String description,
    String publisher,
    String publicationDate,
    String category,
    String language,
    String imageUrl,
    String status) {}
