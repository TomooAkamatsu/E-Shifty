package com.example.sma.domain.models.shift;

import lombok.Data;

@Data
public class Shift {
    private int employeeId;
    private String date;
    private int shiftPatternId;
    private String confirmation;
}
