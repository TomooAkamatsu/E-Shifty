package com.example.sma.application.employee;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.WorkingForm;
import com.example.sma.infrastructure.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeApplicationService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployee() {
        return employeeRepository.findAllEmployee();
    }

    public void insertEmployee(Employee employee) {
            employeeRepository.insertEmployee(employee);
    }

    public void updateEmployee(String key, String value, int employeeId) {
        employeeRepository.updateEmployee(key, value, employeeId);
    }

    public void deleteEmployee(int employeeId) {
        employeeRepository.deleteEmployee(employeeId);
    }

    public List<Integer> getEmployeeIdList(){
        return employeeRepository.findAllEmployee().stream().map(Employee::getEmployeeId).toList();
    }

    public List<String> getEmployeeNameList(){
        return employeeRepository.findAllEmployee().stream().map(Employee::getLastName).toList();
    }

    public List<WorkingForm> findAllWorkingForm() {
        return employeeRepository.findAllWorkingForm();
    }

}
