package com.example.sma.domain.models.shift;

import lombok.Data;

@Data
public class VacationRequest {
    private int requestId;
    private int employeeId;
    private String requestDate;
}
