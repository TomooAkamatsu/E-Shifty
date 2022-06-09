package com.example.sma.presentation.shift;

import com.example.sma.application.employee.EmployeeApplicationService;
import com.example.sma.application.shift.ShiftApplicationService;
import com.example.sma.domain.models.shift.Shift;
import com.example.sma.domain.models.shift.ShiftPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shift")
public class ShiftController {

    private final ShiftApplicationService shiftApplicationService;
    private final EmployeeApplicationService employeeApplicationService;

    @GetMapping("/{year}/{month}")
    public List<ShiftForm> getShift(@PathVariable("year") int year, @PathVariable("month") int month) {

        List<String> employeeNameList = employeeApplicationService.getEmployeeNameList();

        List<List<Shift>> allEmployeeShiftList
                = shiftApplicationService.findShift(year, month, employeeApplicationService.getEmployeeIdList());
        List<ShiftPattern> shiftPatterns = shiftApplicationService.findAllShiftPattern();

        return allEmployeeShiftList
                .stream()
                .map(individualShiftList -> new ShiftForm(individualShiftList, shiftPatterns, employeeNameList))
                .toList();
    }

    @GetMapping("/draft")
    public DraftForm getDraft() {
        List<String> employeeNameList = employeeApplicationService.getEmployeeNameList();
        List<ShiftPattern> shiftPatterns = shiftApplicationService.findAllShiftPattern();

        LocalDateTime now = LocalDateTime.now();

        List<List<Shift>> shiftList = shiftApplicationService.findShift(
                now.getYear(),
                now.getMonthValue(),
                employeeApplicationService.getEmployeeIdList());

        return new DraftForm(shiftList, employeeNameList, shiftPatterns);
    }

}
