package com.example.sma.presentation.employee;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.WorkingForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class EmployeeForm {

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

    private String workingFormName;

    EmployeeForm(Employee employee){
        this.employeeId = employee.getEmployeeId();
        this.lastName = employee.getLastName();
        this.firstName = employee.getFirstName();
        this.romanLastName = employee.getRomanLastName();
        this.romanFirstName = employee.getRomanFirstName();
        this.birthday = employee.getBirthday();
        this.age = employee.getAge();
        this.gender = employee.getGender();
        this.phoneNumber = employee.getPhoneNumber();
        this.email = employee.getEmail();
        this.employmentDate = employee.getEmploymentDate();
        this.workingFormName = employee.getWorkingForms().getWorkingFormName();
    }
//
//    getEmployee
}
