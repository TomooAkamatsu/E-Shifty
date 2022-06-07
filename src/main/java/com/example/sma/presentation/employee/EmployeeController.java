package com.example.sma.presentation.employee;

import com.example.sma.application.employee.EmployeeApplicationService;
import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.WorkingForm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.sma.presentation.employee.EmployeePresentationLogic.*;

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

        String str;
        if (!employeeApplicationService.registerForNewEmployee(employeeForm.convertToEntity()))
            str = "{\"result\":\"failed\"}";
        str = "{\"result\":\"ok\"}";

        return str;
    }

    @GetMapping("/working-form")
    public List<WorkingForm> getAllWorkingForm() {
        return employeeApplicationService.findAllWorkingForm();
    }

    @PatchMapping("/{employeeId}")
    public String patchEmployee(@RequestBody String patchData, @PathVariable("employeeId") int employeeId) {

        Map<String, String> map = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(patchData, new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.entrySet().stream().forEach(e -> employeeApplicationService.updateEmployee(
                camelToSnake(checkWorkingForm(e.getKey())),
                ifWorkingFormGetId(e.getKey(), e.getValue()),
                employeeId));
        return "{\"result\":\"ok\"}";
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") int employeeId) {
        employeeApplicationService.deleteEmployee(employeeId);
        return "{\"result\":\"ok\"}";
    }
}

