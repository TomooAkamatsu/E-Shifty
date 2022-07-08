package com.example.sma.presentation.shift;

import com.example.sma.application.employee.EmployeeApplicationService;
import com.example.sma.application.shift.ShiftApplicationService;
import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.shift.Shift;
import com.example.sma.domain.models.shift.ShiftPattern;
import com.example.sma.domain.models.shift.VacationRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shift")
public class ShiftController {

    private final ShiftApplicationService shiftApplicationService;
    private final EmployeeApplicationService employeeApplicationService;

    @GetMapping("/{year}/{month}")
    public List<ShiftForm> getShift(@PathVariable("year") int year, @PathVariable("month") int month) {

        List<Employee> activeEmployeeListInTheMonth = employeeApplicationService.findActiveEmployeeInTheMonth(year,month);
        List<Integer> employeeIdList = activeEmployeeListInTheMonth.stream().map(Employee::getEmployeeId).toList();

        List<List<Shift>> allEmployeeShiftList
                = shiftApplicationService.findShift(year, month, employeeIdList);
        List<ShiftPattern> shiftPatterns = shiftApplicationService.findAllShiftPattern();

        return allEmployeeShiftList
                .stream()
                .map(individualShiftList -> new ShiftForm(individualShiftList, shiftPatterns,activeEmployeeListInTheMonth))
                .toList();
    }

    @GetMapping("/draft")
    public DraftForm getDraft() {

        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);

        if (shiftApplicationService.shiftDontExist(nextMonth.getYear(), nextMonth.getMonthValue())) {
            try {
                shiftApplicationService.createDraft(employeeApplicationService.findActiveEmployee());
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        List<List<Shift>> shiftList = shiftApplicationService.findShift(
                nextMonth.getYear(),
                nextMonth.getMonthValue(),
                employeeApplicationService.getEmployeeIdList());

        List<Employee> employeeList = employeeApplicationService.findActiveEmployee();
        List<ShiftPattern> shiftPatternList = shiftApplicationService.findAllShiftPattern();

        return new DraftForm(shiftList, employeeList, shiftPatternList);
    }

    @PatchMapping("/draft")
    public String patchDraft(@RequestBody String patchData) {
        Map<String, String> patchDataMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            patchDataMap = mapper.readValue(patchData, new TypeReference<Map<String, String>>() {
            });
            shiftApplicationService.updateDraft(patchDataMap,
                    employeeApplicationService.findActiveEmployee());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "{\"result\":\"ok\"}";
    }

    @GetMapping("/draft/recreation")
    public String draftRecreate() {
        shiftApplicationService.deleteDraft();
        return "hoge";
    }

    @GetMapping("/vacation-requests")
    public List<VacationRequestListForm> getVacationRequestList() {
        List<Integer> employeeIdList = employeeApplicationService.getEmployeeIdList();
        List<Employee> employeeList = employeeApplicationService.findActiveEmployee();
        List<List<VacationRequest>> vacationRequestList = shiftApplicationService.findAllVacationRequest(employeeIdList);

        List<VacationRequestListForm> hoge =
                vacationRequestList.stream().filter(individualRequestList -> !CollectionUtils.isEmpty(individualRequestList))
                        .map(individualRequestList -> new VacationRequestListForm(individualRequestList, employeeList)).toList();

        return hoge;
    }

    @PostMapping("/vacation-requests/{employeeId}")
    public String postVacationRequestList(@RequestBody VacationRequestListForm vacationRequestListForm) {
        if (!shiftApplicationService.registerVacationRequest(vacationRequestListForm)) return "{\"result\":\"false\"}";
        return "{\"result\":\"ok\"}";
    }

    @PutMapping("/vacation-requests/{employeeId}")
    public String patchVacationRequestList(@RequestBody VacationRequestListForm vacationRequestListForm) {
        shiftApplicationService.updateVacationRequest(vacationRequestListForm);
        return "{\"result\":\"ok\"}";
    }

    @GetMapping("/vacation-requests/{employeeId}")
    public VacationRequestListForm getVacationRequest(@PathVariable("employeeId") int employeeId) {
        return new VacationRequestListForm(shiftApplicationService.findVacationRequest(employeeId), employeeId);
    }

    @GetMapping("/patterns")
    public List<ShiftPattern> getShiftPatterns() {
        return shiftApplicationService.findAllShiftPattern();
    }

}
