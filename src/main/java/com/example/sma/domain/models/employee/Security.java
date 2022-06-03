package com.example.sma.domain.models.employee;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Security {
    private int employeeId;
    private String password;
    private String authority;
}
