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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
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

    public List<Shift> createDraft(List<Employee> employeeList, List<ShiftPattern> shiftPatternList) {

        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        int daysOfNextMonth = nextMonth.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

        //来月の土日に対応する数字の取得
        List<Integer> weekendIndex = getWeekendIndex();

        //ほんとは従業員の現職チェックが必要

        List<Shift> draft = new ArrayList<>();
//      draft.size()は従業員数×翌月の日数
//      土日は休み、それ以外には0、ほんとはシフトパターンによって動的に処理したい。
        employeeList.forEach(employee -> {
            IntStream.rangeClosed(1, daysOfNextMonth).forEach(i -> {

                if (weekendIndex.contains(i % 31))
                    draft.add(new Shift(employee.getEmployeeId(),
                            nextMonth.getYear() + "-" + String.format("%02d" ,nextMonth.getMonthValue()) + "-" + String.format("%02d",i),
                            5,
                            "N"
                    ));

                if (!weekendIndex.contains(i % 31))
                    draft.add(new Shift(employee.getEmployeeId(),
                            nextMonth.getYear() + "-" + String.format("%02d" ,nextMonth.getMonthValue()) + "-" + String.format("%02d",i),
                            0,
                            "N"
                    ));

            });
        });

        //vacationListを平くして入れる
        List<VacationRequest> vacationRequestList = new ArrayList<>();
        findAllVacationRequest(employeeList.stream().map(Employee::getEmployeeId).toList())
                .forEach(vacationRequestList::addAll);

//        休み希望に休みに入れる。
        vacationRequestList.forEach(request -> {
            draft.forEach(shift -> {
                if(shift.getEmployeeId() == request.getEmployeeId() &&
                    shift.getDate().equals(request.getRequestDate()))
                    shift.setShiftPatternId(5);
            });
        });

        //早番遅番のセット
        //土日じゃない数字を取得
        //そのうち正社員から1日2人ずつ朝晩と遅番を入れていく。


        //残りはランダムで

        return draft;

    }

    List<Integer> getWeekendIndex() {
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        int daysOfNextMonth = LocalDateTime.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

        List<Integer> weekEndDateList = new ArrayList<>();

        IntStream.rangeClosed(1, daysOfNextMonth).forEach(i -> {
            if (List.of(6, 7).contains(LocalDate.of(nextMonth.getYear(), nextMonth.getMonthValue(), i).getDayOfWeek().getValue()))
                weekEndDateList.add(i);
        });

        return weekEndDateList;
    }
}
