package com.bookstore.userportal.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "coupon_import")
public class CouponImport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_import_id")
    private Long couponImportId;

    @Column(name = "date_import")
    private LocalDate dateImport;

    @Column(name = "price_book")
    private Integer priceBook;

    @Column(name = "number_import")
    private Integer numberImport;

    @Column(name = "total_price")
    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
