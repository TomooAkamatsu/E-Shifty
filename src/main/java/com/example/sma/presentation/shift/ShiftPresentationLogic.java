package com.example.sma.presentation.shift;

import com.example.sma.domain.models.shift.Shift;
import com.example.sma.domain.models.shift.ShiftPattern;

import java.util.List;

public class ShiftPresentationLogic {

    public static List<String> getShiftPatternNameList(List<Shift> individualShiftList, List<ShiftPattern> shiftPatterns) {

        List<Integer> shiftPatternIdList = individualShiftList
                .stream()
                .map(Shift::getShiftPatternId)
                .toList();


        List<String> shiftPatternName= shiftPatterns
                .stream()
                .map(ShiftPattern::getShiftPatternName)
                .toList();

        return shiftPatternIdList
                .stream()
                .map(patternId -> shiftPatternName.get(patternId-1))
                .toList();
    }

}
