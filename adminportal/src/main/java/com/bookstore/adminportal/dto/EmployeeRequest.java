package com.bookstore.adminportal.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequest {

    private Long id;

    private String cartICNumber;

    private String startDate;

    private String employeeName;

    private String address;

    private String phoneNumber;

    private Integer salary;

    private Boolean sex;

    private Boolean active;

    private String username;

    private String password;

    private String email;


}
