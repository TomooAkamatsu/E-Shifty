package com.example.sma.domain.models.shift;

import lombok.Data;

@Data
public class ShiftPattern {

    private int shiftPatternId;
    private String shiftPatternName;
    private String startTime;
    private String endTime;

}
