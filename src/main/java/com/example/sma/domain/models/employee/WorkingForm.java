package com.example.sma.domain.models.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class WorkingForm {
    private int workingFormId;
    private String workingFormName;

}
