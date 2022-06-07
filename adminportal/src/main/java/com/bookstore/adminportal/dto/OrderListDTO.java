package com.bookstore.adminportal.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
public class OrderListDTO {
    private Long userId;
    private String name;
    private Date orderDate;
    private Long orderId;
    private BigDecimal orderTotal;
    private String orderStatus;
}
