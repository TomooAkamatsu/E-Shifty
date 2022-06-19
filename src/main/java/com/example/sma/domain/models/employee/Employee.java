package com.example.sma.domain.models.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public Employee(String lastName, String firstName, String romanLastName, String romanFirstName, String birthday, int age, String gender, String phoneNumber, String email, String employmentDate, String retirementDate, WorkingForm workingForms) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.romanLastName = romanLastName;
        this.romanFirstName = romanFirstName;
        this.birthday = birthday;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.employmentDate = employmentDate;
        this.retirementDate = retirementDate;
        this.workingForms = workingForms;
    }
}
