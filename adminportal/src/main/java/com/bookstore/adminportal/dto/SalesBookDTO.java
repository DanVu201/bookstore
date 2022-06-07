package com.bookstore.adminportal.dto;


import com.bookstore.adminportal.domain.Book;
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
