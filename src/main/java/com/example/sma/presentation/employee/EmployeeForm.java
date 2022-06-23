package com.example.sma.presentation.employee;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.WorkingForm;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.example.sma.presentation.employee.EmployeePresentationLogic.getWorkingFormIdFromName;

//ゲッターを用意しないとJSONをマッピングできない
@Data
@NoArgsConstructor
class EmployeeForm {

    private int employeeId;
    @NotBlank
    @Pattern(regexp = "^[^ -~｡-ﾟ]*$")
    private String lastName;
    @NotBlank
    @Pattern(regexp = "^[^ -~｡-ﾟ]*$")
    private String firstName;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String romanLastName;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String romanFirstName;
    @NotBlank
    @Pattern(regexp = "^[ -~｡-ﾟ]*$")
    private String birthday;
    @Range(min=0,max=120)
    private int age;
    @NotBlank
    @Pattern(regexp = "^[^ -~｡-ﾟ]*$")
    private String gender;
    @NotBlank
    @Pattern(regexp = "^(\\d{3}-?\\d{4}-?\\d{4})?$")
    private String phoneNumber;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "^[ -~｡-ﾟ]*$")
    private String employmentDate;
    @NotBlank
    @Pattern(regexp = "^[^ -~｡-ﾟ]*$")
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
