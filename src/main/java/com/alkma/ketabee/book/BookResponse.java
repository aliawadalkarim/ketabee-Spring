package com.alkma.ketabee.book;

record BookResponse(
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
