package com.bookstore.userportal.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "month")
    private int month;

    @Column(name = "year")
    private int year;

    @Column(name = "category", columnDefinition = "nvarchar(255)")
    private String category;

}
