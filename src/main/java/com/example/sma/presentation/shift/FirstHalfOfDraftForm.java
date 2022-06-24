package com.example.sma.presentation.shift;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.shift.Shift;
import com.example.sma.domain.models.shift.ShiftPattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.IntStream;

import static com.example.sma.presentation.shift.ShiftPresentationLogic.getShiftPatternNameList;

@Data
@NoArgsConstructor
public class FirstHalfOfDraftForm {
    private String employeeName;
    private FirstHalfOfDateListForm shift;

    public FirstHalfOfDraftForm(List<Shift> individualShiftList, List<Employee> employeeList, List<ShiftPattern> shiftPatterns) {
        this.employeeName = employeeList.stream()
                .filter(employee -> individualShiftList.get(0).getEmployeeId()==employee.getEmployeeId())
                .findFirst().orElseThrow().getLastName();

        List<Shift> firstHalfOfIndividualShiftList = IntStream
                .rangeClosed(0, 14)
                .mapToObj(individualShiftList::get)
                .toList();

        this.shift = new FirstHalfOfDateListForm(getShiftPatternNameList(firstHalfOfIndividualShiftList, shiftPatterns));

    }
}
