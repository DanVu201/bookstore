package com.bookstore.adminportal.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String cartICNumber;

    private String startDate;

    @Column(columnDefinition = "nvarchar(255)")
    private String employeeName;

    private String phoneNumber;

    @Column(columnDefinition = "nvarchar(255)")
    private String address;

    private Integer salary;

    private Boolean sex;

    private Boolean active;

    @OneToMany
    private List<Order> orderList;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
