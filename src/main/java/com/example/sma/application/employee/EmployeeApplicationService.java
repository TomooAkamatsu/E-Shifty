package com.example.sma.application.employee;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.WorkingForm;
import com.example.sma.exception.NotFoundEmployeeException;
import com.example.sma.infrastructure.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeApplicationService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployee() {
        return employeeRepository.findAllEmployee();
    }

    public boolean insertEmployee(Employee employee) {
        try {
            employeeRepository.insertEmployee(employee);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean updateEmployee(Map<String, String> patchDataMap, int employeeId) {

        try {
            Employee targetEmployee = employeeRepository.findOneEmployee(employeeId)
                    .orElseThrow(() -> new NotFoundEmployeeException("対象の従業員が見つかりませんでした"));

            if (patchDataMap.containsKey("lastName"))
                targetEmployee.setLastName(patchDataMap.get("lastName"));
            if (patchDataMap.containsKey("firstName"))
                targetEmployee.setFirstName(patchDataMap.get("firstName"));
            if (patchDataMap.containsKey("romanLastName"))
                targetEmployee.setRomanLastName(patchDataMap.get("romanLastName"));
            if (patchDataMap.containsKey("romanFirstName"))
                targetEmployee.setRomanFirstName(patchDataMap.get("romanFirstName"));
            if (patchDataMap.containsKey("birthday"))
                targetEmployee.setBirthday(patchDataMap.get("birthday"));
            if (patchDataMap.containsKey("age"))
                targetEmployee.setAge(Integer.parseInt(patchDataMap.get("age")));
            if (patchDataMap.containsKey("gender"))
                targetEmployee.setGender(patchDataMap.get("gender"));
            if (patchDataMap.containsKey("phoneNumber"))
                targetEmployee.setPhoneNumber(patchDataMap.get("phoneNumber"));
            if (patchDataMap.containsKey("email"))
                targetEmployee.setEmail(patchDataMap.get("email"));
            if (patchDataMap.containsKey("employmentDate"))
                targetEmployee.setEmploymentDate(patchDataMap.get("employmentDate"));
            if (patchDataMap.containsKey("workingFormName")) {
                List<WorkingForm> workingFormList = employeeRepository.findAllWorkingForm();
                workingFormList.forEach(workingForm -> {
                    if (patchDataMap.get("workingFormName").equals(workingForm.getWorkingFormName()))
                        targetEmployee.setWorkingForms(workingForm);
                });
            }
            employeeRepository.updateEmployee(targetEmployee);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteEmployee(int employeeId) {
        try {
            employeeRepository.deleteEmployee(employeeId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Integer> getEmployeeIdList() {
        return employeeRepository.findAllEmployee().stream().map(Employee::getEmployeeId).toList();
    }

    public List<String> getEmployeeNameList() {
        return employeeRepository.findAllEmployee().stream().map(Employee::getLastName).toList();
    }

    public List<WorkingForm> findAllWorkingForm() {
        return employeeRepository.findAllWorkingForm();
    }

}
