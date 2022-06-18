package com.example.sma.domain.models.shift;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//テストのためにNoArgsとAllArgsを追加
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftPattern {

    private int shiftPatternId;
    private String shiftPatternName;
    private String startTime;
    private String endTime;

}
