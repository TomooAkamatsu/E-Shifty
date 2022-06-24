package com.example.sma.presentation.employee;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmployeeCreationResult {
    private final boolean completed;
    private final int insertionEmployeeId;
}
