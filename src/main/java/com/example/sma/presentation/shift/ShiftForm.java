package com.example.sma.presentation.shift;

import com.example.sma.domain.models.shift.Shift;
import com.example.sma.domain.models.shift.ShiftPattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class ShiftForm {
    private String employeeName;
    private String[] patternArr;

//    workingFormIdやemployeeIdの照合はget(index)を使用しない方が良いだろうけど、とりあえず
    ShiftForm(List<Shift> individualShiftList, List<ShiftPattern> shiftPatterns, List<String> employeeNameList){
        this.employeeName = employeeNameList.get(individualShiftList.get(0).getEmployeeId() - 1);

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
