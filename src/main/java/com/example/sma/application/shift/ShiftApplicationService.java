package com.example.sma.application.shift;

import com.example.sma.domain.models.shift.Shift;
import com.example.sma.domain.models.shift.ShiftPattern;
import com.example.sma.domain.models.shift.VacationRequest;
import com.example.sma.infrastructure.shift.ShiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftApplicationService {

    private final ShiftRepository shiftRepository;

    public List<List<Shift>> findShift(int year, int month, List<Integer> employeeIdList) {
        List<List<Shift>> shiftList = new ArrayList<>();
        employeeIdList.stream().forEach(employeeId -> shiftList.add(shiftRepository.findShift(year, month, employeeId)));
        return shiftList;
    }

    public List<ShiftPattern> findAllShiftPattern() {
        return shiftRepository.findAllShiftPattern();
    }

    public List<List<VacationRequest>> findAllVacationRequest(List<Integer> employeeIdList) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextMonth = now.plusMonths(1);
        List<List<VacationRequest>> vacationRequests =
                employeeIdList
                        .stream()
                        .map(employeeId -> shiftRepository.findAllVacationRequest(nextMonth.getYear(),
                                                                            nextMonth.getMonthValue(),
                                                                            employeeId))
                        .toList();

        return vacationRequests;
    }
}
