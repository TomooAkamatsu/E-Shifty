package com.example.sma.domain.models.shift;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShiftPattern {

    private int shiftPatternId;
    private String shiftPatternName;
    private String startTime;
    private String endTime;

}
