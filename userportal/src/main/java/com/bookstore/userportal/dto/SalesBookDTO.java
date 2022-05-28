package com.bookstore.userportal.dto;

import com.bookstore.userportal.domain.Book;
import lombok.*;

import java.util.Optional;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesBookDTO {
    private int quantity;
    private Optional<Book> book;
}
