package com.example.sma.application.shift;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.shift.Shift;
import com.example.sma.domain.models.shift.ShiftPattern;
import com.example.sma.domain.models.shift.VacationRequest;
import com.example.sma.exception.DraftCreationException;
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
        employeeIdList.stream().forEach(employeeId -> shiftList.add(shiftRepository.findIndividualMonthlyShift(year, month, employeeId)));
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

    public void createDraft(List<Employee> employeeList) throws DraftCreationException {

        /*
         *【大まかな流れ】
         * ・従業員数×翌月の日数サイズのList<Shift>を作成
         * ・土日に休みを入れる
         * ・休み希望日に休みを入れる
         * ・朝晩と遅番を営業日に一人ずつ入れる
         * ・残りを半々になるようシフトを入れる
         *
         * todo: シフトパターンに絡む処理は本来は動的に変更できるようにするべき
         * todo: 登録されている従業員が全員現職の前提で進めているが本来はチェックを入れるべき
         */

        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        int daysOfNextMonth = nextMonth.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

        List<Shift> draft = new ArrayList<>();

        //来月の土日に対応する数字の取得
        List<Integer> weekendDateList = getWeekend();

        //draftの型を作成,draft.size()は従業員数×翌月の日数
        employeeList.forEach(employee -> {
            IntStream.rangeClosed(1, daysOfNextMonth).forEach(i -> {
                //土日は休み、それ以外には0
                if (weekendDateList.contains(i % daysOfNextMonth))
                    draft.add(new Shift(employee.getEmployeeId(),
                            nextMonth.getYear() + "-" + String.format("%02d", nextMonth.getMonthValue()) + "-" + String.format("%02d", i),
                            5,
                            "N"
                    ));
                if (!weekendDateList.contains(i % daysOfNextMonth))
                    draft.add(new Shift(employee.getEmployeeId(),
                            nextMonth.getYear() + "-" + String.format("%02d", nextMonth.getMonthValue()) + "-" + String.format("%02d", i),
                            0,
                            "N"
                    ));
            });
        });

        //休み希望リストを取得して、ネスト構造のListを一段解消する
        List<VacationRequest> vacationRequestList = new ArrayList<>();
        findAllVacationRequest(employeeList.stream().map(Employee::getEmployeeId).toList())
                .forEach(vacationRequestList::addAll);

        //休み希望に休みに入れる
        vacationRequestList.forEach(request -> {
            draft.forEach(shift -> {
                if (shift.getEmployeeId() == request.getEmployeeId() &&
                        shift.getDate().equals(request.getRequestDate()))
                    shift.setShiftPatternId(5);
            });
        });

        //正社員リスト
        List<Integer> fullTimeEmployeeIdList = employeeList.stream()
                .filter(employee -> employee.getWorkingForms().getWorkingFormId() == 1)
                .map(Employee::getEmployeeId).toList();

        // ここまでシフトが入っていない(ShiftPatternが0の)Shiftのリスト
        List<Integer> emptyShiftPatternIndexList = new ArrayList<>();
        int count = 1;
        for (Shift shift : draft) {
            if (shift.getShiftPatternId() == 0) emptyShiftPatternIndexList.add(count);
            count++;
        }

        //来月の営業日を取得
        List<Integer> businessDateList = getBusinessDay();

        //1日から月末までfor文で繰り返し
        for (int date = 1; date <= daysOfNextMonth; date++) {

            //日毎のシフトが入っていない従業員の抽出
            List<Integer> dailyEmptyShiftList = new ArrayList<>();
            for (int i : emptyShiftPatternIndexList) {
                if (i % daysOfNextMonth == date)
                    dailyEmptyShiftList.add(i);
            }

            //その日のシフトが入っていない従業員のうち、早番と遅番に入れる正社員の抽出
            List<Integer> employeeWhoCanWorkEarlyAndLate = new ArrayList<>(dailyEmptyShiftList.stream().filter(index -> fullTimeEmployeeIdList.contains(index / daysOfNextMonth + 1)).toList());

            //人が少なく早番と遅番を割り当てられない場合はここで例外をスロー
            if (employeeWhoCanWorkEarlyAndLate.size() == 1) throw new DraftCreationException(date + "の組み合わせがないためシフトを作成できません");

            //dateが営業日の場合
            if (businessDateList.contains(date)) {

                //ランダムに早番と遅番を入れる
                Collections.shuffle(employeeWhoCanWorkEarlyAndLate);
                draft.get(employeeWhoCanWorkEarlyAndLate.get(0) - 1).setShiftPatternId(1);
                draft.get(employeeWhoCanWorkEarlyAndLate.get(1) - 1).setShiftPatternId(4);

                dailyEmptyShiftList.removeAll(List.of(
                        employeeWhoCanWorkEarlyAndLate.get(0),
                        employeeWhoCanWorkEarlyAndLate.get(1)
                ));

                //ランダムに残りのシフトを入れる
                int k = dailyEmptyShiftList.size() / 2;
                List<Integer> intList = new ArrayList<>(Arrays.asList(k, dailyEmptyShiftList.size() - k));
                Collections.shuffle(intList);
                Collections.shuffle(dailyEmptyShiftList);

                IntStream.range(0, intList.get(0)).forEach(j -> draft.get(dailyEmptyShiftList.get(j) - 1).setShiftPatternId(2));
                IntStream.range(intList.get(0), dailyEmptyShiftList.size()).forEach(j -> draft.get(dailyEmptyShiftList.get(j) - 1).setShiftPatternId(3));
            }
        }

        //確認用
//        IntStream.rangeClosed(1, 31).forEach(i -> System.out.print(draft.get(i - 1).getShiftPatternId()));
//        System.out.println("");
//        IntStream.rangeClosed(32, 62).forEach(i -> System.out.print(draft.get(i - 1).getShiftPatternId()));
//        System.out.println("");
//        IntStream.rangeClosed(63, 93).forEach(i -> System.out.print(draft.get(i - 1).getShiftPatternId()));
//        System.out.println("");
//        IntStream.rangeClosed(94, 124).forEach(i -> System.out.print(draft.get(i - 1).getShiftPatternId()));
//        System.out.println("");
//        IntStream.rangeClosed(125, 155).forEach(i -> System.out.print(draft.get(i - 1).getShiftPatternId()));
//        System.out.println("");
//        IntStream.rangeClosed(156, 186).forEach(i -> System.out.print(draft.get(i - 1).getShiftPatternId()));
//        System.out.println("");
//        IntStream.rangeClosed(187, 217).forEach(i -> System.out.print(draft.get(i - 1).getShiftPatternId()));
//        System.out.println("");
//        IntStream.rangeClosed(218, 248).forEach(i -> System.out.print(draft.get(i - 1).getShiftPatternId()));
//        System.out.println("");
//        IntStream.rangeClosed(249, 279).forEach(i -> System.out.print(draft.get(i - 1).getShiftPatternId()));
//        System.out.println("");
//        IntStream.rangeClosed(280, 310).forEach(i -> System.out.print(draft.get(i - 1).getShiftPatternId()));
//        System.out.println("");
//        System.out.println("");

        draft.forEach(shiftRepository::insertShift);
    }

    List<Integer> getWeekend() {
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        int daysOfNextMonth = LocalDateTime.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

        List<Integer> weekEndDateList = new ArrayList<>();

        IntStream.rangeClosed(1, daysOfNextMonth).forEach(i -> {
            if (List.of(6, 7).contains(LocalDate.of(nextMonth.getYear(), nextMonth.getMonthValue(), i).getDayOfWeek().getValue()))
                weekEndDateList.add(i);
        });

        if (weekEndDateList.contains(daysOfNextMonth)) {
            weekEndDateList.remove(Integer.valueOf(daysOfNextMonth));
            weekEndDateList.add(0);
        }
        return weekEndDateList;
    }

    List<Integer> getBusinessDay() {
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        int daysOfNextMonth = LocalDateTime.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

        List<Integer> weekEndDateList = new ArrayList<>();

        IntStream.rangeClosed(1, daysOfNextMonth).forEach(i -> {
            if (List.of(1, 2, 3, 4, 5).contains(LocalDate.of(nextMonth.getYear(), nextMonth.getMonthValue(), i).getDayOfWeek().getValue()))
                weekEndDateList.add(i);
        });

        return weekEndDateList;
    }

    public boolean shiftDontExist(int year, int month) {
        if (shiftRepository.findMonthlyShift(year, month).equals(Collections.EMPTY_LIST)) return true;
        return false;
    }

    public void updateDraft(Map<String, String> patchDataMap, List<Employee> employeesList) {
        List<ShiftPattern> shiftPatternList = findAllShiftPattern();

        ShiftPattern changedShiftPattern = shiftPatternList.stream()
                .filter(shiftPattern -> shiftPattern.getShiftPatternName().equals(patchDataMap.get("changedPattern")))
                .findFirst().orElseThrow();
        Employee targetEmployee = employeesList.stream()
                .filter(employee -> employee.getLastName().equals(patchDataMap.get("targetEmployeeName")))
                .findFirst().orElseThrow();

        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        int targetDate = Integer.parseInt(patchDataMap.get("targetDate").replace("date","").replace("th", ""));
        Shift patchShift = new Shift(
                targetEmployee.getEmployeeId(),
                String.valueOf(nextMonth.getYear()) + "-" + String.format("%02d", nextMonth.getMonthValue()) + "-" + String.format("%02d", targetDate),
                changedShiftPattern.getShiftPatternId(),
                "N"
        );
        shiftRepository.updateShift(patchShift);
    }

    public void deleteDraft() {
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        shiftRepository.deleteShift(nextMonth.getYear(),nextMonth.getMonthValue());
    }
}
