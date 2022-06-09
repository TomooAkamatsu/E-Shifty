package com.example.sma.application.employee;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.WorkingForm;
import com.example.sma.infrastructure.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeApplicationService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployee() {
        return employeeRepository.findAllEmployee();
    }

    public List<WorkingForm> findAllWorkingForm() {
        return employeeRepository.findAllWorkingForm();
    }

    public boolean registerForNewEmployee(Employee employee) {
        try {
            employeeRepository.registerForNewEmployee(employee);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Employee findOneEmployee(int employeeId) {
        return employeeRepository.findOneEmployee(employeeId);
    }

    public boolean updateEmployee(String key, String value, int employeeId) {
        employeeRepository.updateEmployee(key, value, employeeId);
        return true;
    }

    public boolean deleteEmployee(int employeeId) {
        employeeRepository.deleteEmployee(employeeId);
        return true;
    }

    public List<Integer> getEmployeeIdList(){
        List<Integer> employeeIdList = new ArrayList<>();
        employeeRepository.findAllEmployee().stream().forEach(employee -> employeeIdList.add(employee.getEmployeeId()));
        return employeeIdList;
    }

    public List<String> getEmployeeNameList(){
        List<String> employeeNameList = new ArrayList<>();
        employeeRepository.findAllEmployee().stream().forEach(employee -> employeeNameList.add(employee.getLastName()));
        return employeeNameList;
    }

}
