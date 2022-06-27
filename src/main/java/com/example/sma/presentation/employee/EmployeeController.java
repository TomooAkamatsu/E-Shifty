package com.example.sma.presentation.employee;

import com.example.sma.application.employee.EmployeeApplicationService;
import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.WorkingForm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<EmployeeCreationResult> postEmployee(@RequestBody @Validated EmployeeForm employeeForm) {
        EmployeeCreationResult result = employeeApplicationService.insertEmployee(employeeForm.convertToEntity());

        URI location = URI.create("http://localhost:8080/api/employees/" + result.getInsertionEmployeeId() );

        return ResponseEntity.created(location).body(result);
    }

    @PatchMapping("/{employeeId}")
    public String patchEmployee(@RequestBody String patchData, @PathVariable("employeeId") int employeeId) {

        Map<String, String> patchDataMap = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            patchDataMap = mapper.readValue(patchData, new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return employeeApplicationService.updateEmployee(patchDataMap, employeeId);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") int employeeId) {
        return employeeApplicationService.deleteEmployee(employeeId);
    }

    @GetMapping("/working-form")
    public List<WorkingForm> getAllWorkingForm() {
        return employeeApplicationService.findAllWorkingForm();
    }

//    魔法のボタン
    @GetMapping("/reset")
    public String reset(){
        employeeApplicationService.reset();
        return  "{\"message\":\"くらぁーーっ!!\"}";
    }

}

