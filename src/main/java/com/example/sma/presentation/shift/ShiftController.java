package com.example.sma.presentation.shift;

import com.example.sma.application.employee.EmployeeApplicationService;
import com.example.sma.application.shift.ShiftApplicationService;
import com.example.sma.domain.models.shift.Shift;
import com.example.sma.domain.models.shift.ShiftPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shift")
public class ShiftController {

    private final ShiftApplicationService shiftApplicationService;
    private final EmployeeApplicationService employeeApplicationService;

    @GetMapping("/{year}/{month}")
    public List<ShiftForm> getShift(@PathVariable("year") int year, @PathVariable("month") int month){

        List<Integer> employeeIdList = employeeApplicationService.getEmployeeIdList();
        List<String> employeeNameList = employeeApplicationService.getEmployeeNameList();

        List<List<Shift>> shiftList =shiftApplicationService.findShift(year, month, employeeIdList);
        List<ShiftPattern> shiftPatterns = shiftApplicationService.findAllShiftPattern();

        List<ShiftForm> shiftFormList = new ArrayList<>();
        shiftList.stream().forEach(individualShiftList -> shiftFormList.add(new ShiftForm(individualShiftList,shiftPatterns,employeeNameList)));
        return shiftFormList;
    }

}
