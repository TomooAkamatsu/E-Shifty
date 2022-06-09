package com.example.sma.presentation.shift;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FirstHalfOfDateListForm {
    private String date1st;
    private String date2nd;
    private String date3rd;
    private String date4th;
    private String date5th;
    private String date6th;
    private String date7th;
    private String date8th;
    private String date9th;
    private String date10th;
    private String date11th;
    private String date12th;
    private String date13th;
    private String date14th;
    private String date15th;



    public FirstHalfOfDateListForm(List<String> shiftPatternNameList) {
        this.date1st = shiftPatternNameList.get(0);
        this.date2nd = shiftPatternNameList.get(1);
        this.date3rd = shiftPatternNameList.get(2);
        this.date4th = shiftPatternNameList.get(3);
        this.date5th = shiftPatternNameList.get(4);
        this.date6th = shiftPatternNameList.get(5);
        this.date7th = shiftPatternNameList.get(6);
        this.date8th = shiftPatternNameList.get(7);
        this.date9th = shiftPatternNameList.get(8);
        this.date10th = shiftPatternNameList.get(9);
        this.date11th = shiftPatternNameList.get(10);
        this.date12th = shiftPatternNameList.get(11);
        this.date13th = shiftPatternNameList.get(12);
        this.date14th = shiftPatternNameList.get(13);
        this.date15th = shiftPatternNameList.get(14);
    }
}
