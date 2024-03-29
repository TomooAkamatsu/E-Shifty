package com.example.sma.presentation.employee;

import com.example.sma.application.employee.EmployeeApplicationService;
import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.Security;
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

    @GetMapping("login/{employeeId}")
    public Security login(@PathVariable("employeeId") int employeeId){
        return employeeApplicationService.getLoginInfo(employeeId);
    }

    @GetMapping
    public List<EmployeeForm> getAllEmployee() {
        List<Employee> employees = employeeApplicationService.findActiveEmployee();
        List<EmployeeForm> employeeFormList = new ArrayList<>();
        employees.forEach(employee -> employeeFormList.add(new EmployeeForm(employee)));
        return employeeFormList;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<EmployeeOperationResult> postEmployee(@RequestBody @Validated EmployeeForm employeeForm) {
        EmployeeOperationResult result = employeeApplicationService.insertEmployee(employeeForm.convertToEntity());

        URI location = URI.create("http://localhost:8080/api/employees/" + result.getTargetEmployeeId() );

        return ResponseEntity.created(location).body(result);
    }

    @PatchMapping("/{employeeId}")
    public EmployeeOperationResult patchEmployee(@RequestBody String patchData, @PathVariable("employeeId") int employeeId) {

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
    public EmployeeOperationResult patchRetirement(@PathVariable("employeeId") int employeeId) {
        return employeeApplicationService.patchRetirement(employeeId);
    }

    @GetMapping("/working-form")
    public List<WorkingForm> getAllWorkingForm() {
        return employeeApplicationService.findAllWorkingForm();
    }

//    ポートフォリオ用のリセットボタン
    @GetMapping("/reset")
    public EmployeeOperationResult reset(){
        employeeApplicationService.reset();
        return  new EmployeeOperationResult(true);
    }

}

