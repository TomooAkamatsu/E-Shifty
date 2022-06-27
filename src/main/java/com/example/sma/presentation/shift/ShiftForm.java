package com.example.sma.presentation.shift;

import com.example.sma.domain.models.shift.Shift;
import com.example.sma.domain.models.shift.ShiftPattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Data
public class ShiftForm {
    private String employeeName;
    private String[] patternArr;

//    workingFormIdやemployeeIdの照合はget(index)を使用しない方が良いだろうけど、とりあえず
    ShiftForm(List<Shift> individualShiftList, List<ShiftPattern> shiftPatterns){

//        一時的な処理
        Map<Integer,String> employeeMap = new LinkedHashMap<>();

        employeeMap.put(1,"岸田");
        employeeMap.put(2,"菅");
        employeeMap.put(3,"安倍");
        employeeMap.put(4,"野田");
        employeeMap.put(5,"菅");
        employeeMap.put(6,"鳩山");
        employeeMap.put(7,"麻生");
        employeeMap.put(8,"福田");
        employeeMap.put(9,"小泉");
        employeeMap.put(10,"森");


        this.employeeName = employeeMap.get(individualShiftList.get(0).getEmployeeId());

        List<Integer> shiftPatternIdList = new ArrayList<>();
        individualShiftList.forEach(shift -> shiftPatternIdList.add(shift.getShiftPatternId()));

        List<String> shiftPatternName= new ArrayList<>();
        shiftPatterns.forEach(shiftPattern -> shiftPatternName.add(shiftPattern.getShiftPatternName()));

        List<String> shiftPatternNameList = new ArrayList<>();
        shiftPatternIdList.forEach(patternId -> shiftPatternNameList.add(shiftPatternName.get(patternId - 1)));

        String[] str = new String[shiftPatternNameList.size()];
        this.patternArr = shiftPatternNameList.toArray(str);
    }
}
