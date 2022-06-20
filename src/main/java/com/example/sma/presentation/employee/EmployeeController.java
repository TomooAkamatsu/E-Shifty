package com.example.sma.presentation.employee;

import com.example.sma.application.employee.EmployeeApplicationService;
import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.WorkingForm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeApplicationService employeeApplicationService;

    @GetMapping
    public List<EmployeeForm> getAllEmployee() {
        List<Employee> employees = employeeApplicationService.findAllEmployee();
        List<EmployeeForm> employeeFormList = new ArrayList<>();
        employees.forEach(employee -> employeeFormList.add(new EmployeeForm(employee)));
        return employeeFormList;
    }

    @PostMapping
    public String postEmployee(@RequestBody EmployeeForm employeeForm) {
        employeeApplicationService.insertEmployee(employeeForm.convertToEntity());
        return "{\"result\":\"ok\"}";
    }

    @PatchMapping("/{employeeId}")
    public String patchEmployee(@RequestBody String patchData, @PathVariable("employeeId") int employeeId) {

        Map<String, String> patchDataMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            patchDataMap = mapper.readValue(patchData, new TypeReference<Map<String, String>>() {
            });
            employeeApplicationService.updateEmployee(patchDataMap, employeeId);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"result\":\"false\"}";
        }
        return "{\"result\":\"ok\"}";
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") int employeeId) {
        employeeApplicationService.deleteEmployee(employeeId);
        return "{\"result\":\"ok\"}";
    }

    @GetMapping("/working-form")
    public List<WorkingForm> getAllWorkingForm() {
        return employeeApplicationService.findAllWorkingForm();
    }
}

