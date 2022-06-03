package com.example.sma.domain.models.employee;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {

    private int employeeId;
    private String lastName;
    private String firstName;
    private String romanLastName;
    private String romanFirstName;
    private String birthday;
    private int age;
    private String gender;
    private String phoneNumber;
    private String email;
    private String employmentDate;
    private String retirementDate;
    private int workingFormId;

}
