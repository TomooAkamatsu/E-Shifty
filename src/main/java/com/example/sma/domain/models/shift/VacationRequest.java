package com.example.sma.domain.models.shift;

import lombok.Data;

@Data
public class VacationRequest {
    private final int requestId;
    private final int employeeId;
    private final String requestDate;
}
