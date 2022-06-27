package com.example.sma.presentation.shift;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.shift.VacationRequest;
import com.example.sma.exception.NotFoundEmployeeException;
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


    public VacationRequestListForm(List<VacationRequest> vacationRequestList, List<Employee> employeeList) {
        this.employeeId = vacationRequestList.get(0).getEmployeeId();

        this.employeeName = employeeList.stream()
                .filter(employee -> employee.getEmployeeId()==this.employeeId).findFirst()
                .orElseThrow(() -> new NotFoundEmployeeException("従業員が見つかりませんでした")).getLastName();
//        this.employeeName = employeeNameList.get(employeeId - 1);

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
