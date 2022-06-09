package com.example.sma.presentation.shift;

import com.example.sma.domain.models.shift.VacationRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class VacationRequestListForm {
    private int employeeId;
    private String employeeName;
    private String[] requestDate;


    public VacationRequestListForm(List<VacationRequest> vacationRequestList, List<String> employeeNameList) {

        this.employeeId = vacationRequestList.get(0).getEmployeeId();
        this.employeeName = employeeNameList.get(employeeId - 1);

        this.requestDate = vacationRequestList
                .stream()
                .map(request->request.getRequestDate())
                .toList().toArray(new String[vacationRequestList.size()]);
    }
}
