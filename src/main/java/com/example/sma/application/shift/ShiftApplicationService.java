package com.example.sma.application.shift;

import com.example.sma.domain.models.shift.Shift;
import com.example.sma.domain.models.shift.ShiftPattern;
import com.example.sma.infrastructure.shift.ShiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
