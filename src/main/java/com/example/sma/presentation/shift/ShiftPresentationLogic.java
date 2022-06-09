package com.example.sma.presentation.shift;

import com.example.sma.domain.models.shift.Shift;
import com.example.sma.domain.models.shift.ShiftPattern;

import java.util.ArrayList;
import java.util.List;

public class ShiftPresentationLogic {

    public static List<String> getShiftPatternNameList(List<Shift> individualShiftList, List<ShiftPattern> shiftPatterns) {

        List<Integer> shiftPatternIdList = new ArrayList<>(individualShiftList
                .stream()
                .map(Shift::getShiftPatternId)
                .toList());


        List<String> shiftPatternName= new ArrayList<>(shiftPatterns
                .stream()
                .map(ShiftPattern::getShiftPatternName)
                .toList());

        return new ArrayList<>(shiftPatternIdList
                .stream()
                .map(patternId -> shiftPatternName.get(patternId-1))
                .toList());
    }

}
