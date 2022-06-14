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

    public boolean insertEmployee(Employee employee) {
        try {
            employeeRepository.insertEmployee(employee);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean updateEmployee(String key, String value, int employeeId) {
        try {
            employeeRepository.updateEmployee(key, value, employeeId);
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
