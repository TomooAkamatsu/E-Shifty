package com.example.sma.presentation.employee;

import com.example.sma.application.employee.EmployeeApplicationService;
import com.example.sma.domain.models.employee.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeApplicationService employeeApplicationService;

    @GetMapping
    public List<EmployeeForm> findAllEmployee() {
        List<Employee> employees = employeeApplicationService.findAllEmployee();
        List<EmployeeForm> employeeFormList = new ArrayList<>();
        employees.forEach(employee -> employeeFormList.add(new EmployeeForm(employee)));
        return employeeFormList;
    }

    @PostMapping
    public String postEmployee(@RequestBody EmployeeForm employeeForm){

        System.out.println(employeeForm.getAge());

        String str = "{\"result\":\"ok\"}";

        return str;
    }

}

