package com.example.sma.presentation.employee;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.WorkingForm;
import lombok.Data;
import lombok.NoArgsConstructor;


import static com.example.sma.presentation.employee.EmployeePresentationLogic.getWorkingFormIdFromName;

//ゲッターを用意しないとJSONをマッピングできない
@Data
@NoArgsConstructor
final class EmployeeForm {

    //    初期化されないかもとうるさいので非final
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

    EmployeeForm(final Employee employee) {
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

    final Employee convertToEntity() {
        WorkingForm workingForm = new WorkingForm(
                Integer.parseInt(getWorkingFormIdFromName(this.workingFormName)),
                this.workingFormName);

        return new Employee(0,
                this.lastName,
                this.firstName,
                this.romanLastName,
                this.romanFirstName,
                this.birthday,
                this.age,
                this.gender,
                this.phoneNumber,
                this.email,
                this.employmentDate,
                null,
                workingForm);
    }

}
