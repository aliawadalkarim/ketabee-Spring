package com.alkma.ketabee.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record BookRequest(
    @NotBlank
    String title,
    @NotBlank
    String author,
    @Pattern(regexp = "^[0-9]{13}$")
    String isbn,
    @NotNull
    @Positive
    double price,
    @NotBlank
    String description,
    @NotBlank
    String publisher,
    @NotBlank
    String publicationDate,
    @NotBlank
    String category,
    String language,
    String imageUrl,
    String status) {}