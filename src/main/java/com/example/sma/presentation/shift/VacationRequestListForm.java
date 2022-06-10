package com.example.sma.presentation.shift;

import com.example.sma.domain.models.shift.VacationRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

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
                .map(VacationRequest::getRequestDate)
                .toList().toArray(new String[vacationRequestList.size()]);
    }

    public VacationRequestListForm(List<VacationRequest> vacationRequestList, int employeeId) {
        if(CollectionUtils.isEmpty(vacationRequestList)){
            this.employeeId=employeeId;
            this.requestDate= new String[]{};
            return;
        }

        this.employeeId = vacationRequestList.get(0).getEmployeeId();
        this.requestDate = vacationRequestList
                .stream()
                .map(VacationRequest::getRequestDate)
                .toList().toArray(new String[vacationRequestList.size()]);
    }
}
