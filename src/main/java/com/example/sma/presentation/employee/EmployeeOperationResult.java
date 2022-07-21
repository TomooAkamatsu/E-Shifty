package com.example.sma.presentation.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeOperationResult {
    private boolean completed;
    private int targetEmployeeId;

    EmployeeOperationResult(boolean completed){
        this.completed = completed;
    }
}

