package com.example.sma.domain.models.shift;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shift {
    private int employeeId;
    private String date;
    private int shiftPatternId;
    private String confirmation;
}
