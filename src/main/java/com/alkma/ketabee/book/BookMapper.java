package com.alkma.ketabee.book;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    Book toBook(BookRequest bookRequest);
    BookResponse toBookResponse(Book book);
    
}
