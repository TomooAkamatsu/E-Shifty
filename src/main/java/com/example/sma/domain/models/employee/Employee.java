package com.example.sma.domain.models.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

//NoArgsを用意しないとAllArgsを参照するためマッピングに失敗する
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private WorkingForm workingForms;

}
