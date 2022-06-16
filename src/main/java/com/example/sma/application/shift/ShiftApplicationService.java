package com.example.sma.application.shift;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.shift.Shift;
import com.example.sma.domain.models.shift.ShiftPattern;
import com.example.sma.domain.models.shift.VacationRequest;
import com.example.sma.infrastructure.shift.ShiftRepository;
import com.example.sma.presentation.shift.VacationRequestListForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        List<List<VacationRequest>> vacationRequests =
                employeeIdList
                        .stream()
                        .map(employeeId -> shiftRepository.findVacationRequest(nextMonth.getYear(),
                                nextMonth.getMonthValue(),
                                employeeId))
                        .toList();

        return vacationRequests;
    }

    public boolean registerVacationRequest(VacationRequestListForm vacationRequestListForm) {
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);

        List<VacationRequest> vacationRequests =
                shiftRepository.findVacationRequest(nextMonth.getYear(), nextMonth.getMonthValue(), vacationRequestListForm.getEmployeeId());

        if (!CollectionUtils.isEmpty(vacationRequests)) return false;

//        空送信された際のバリデーション、現在は正常にとりあえず動いてるけど。。。。
        Arrays.stream(vacationRequestListForm.getRequestDate()).forEach(date ->
                shiftRepository.insertVacationRequest(vacationRequestListForm.getEmployeeId(), date)
        );

        return true;
    }

    public void updateVacationRequest(VacationRequestListForm vacationRequestListForm) {
        deleteVacationRequest(vacationRequestListForm);
        registerVacationRequest(vacationRequestListForm);
    }

    public void deleteVacationRequest(VacationRequestListForm vacationRequestListForm) {
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        shiftRepository.deleteVacationRequest(nextMonth.getYear(), nextMonth.getMonthValue(), vacationRequestListForm.getEmployeeId());

    }

    public List<VacationRequest> findVacationRequest(int employeeId) {
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        return shiftRepository.findVacationRequest(nextMonth.getYear(), nextMonth.getMonthValue(), employeeId);
    }

    public List<List<String>> createDraft(List<Employee> employeeList, List<ShiftPattern> shiftPatternList) {
        int daysOfNextMonth = LocalDateTime.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth() - 1;

        List<List<String>> draft = new ArrayList<>();
        IntStream.range(0, employeeList.size()).forEach(i -> {
            List<String> individualDraft = new ArrayList<>();
            IntStream.range(0, daysOfNextMonth).forEach(j -> individualDraft.add(""));
            draft.add(individualDraft);
        });

        //来月の土日に対応する数字の取得
        //休みをセット

        //早番遅番のセット

        //残りはランダムで

        return draft;

    }

    private int getVacationIndex(){
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);



        return 1;
    }
}
