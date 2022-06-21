package com.example.sma.presentation.shift;

import com.example.sma.domain.models.shift.Shift;
import com.example.sma.domain.models.shift.ShiftPattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.IntStream;

import static com.example.sma.presentation.shift.ShiftPresentationLogic.getShiftPatternNameList;

@Data
@NoArgsConstructor
public class LatterHalfOfDraftForm {
    private String employeeName;
    private LatterHalfOfDateListForm shift;

    public LatterHalfOfDraftForm(List<Shift> individualShiftList, List<String> employeeNameList, List<ShiftPattern> shiftPatterns) {
        this.employeeName = employeeNameList.get(individualShiftList.get(0).getEmployeeId() - 1);

        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);

        List<Shift> latterHalfOfIndividualShiftList = IntStream
                .rangeClosed(15, nextMonth.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth() - 1)
                .mapToObj(individualShiftList::get)
                .toList();


        List<String> shiftPatternNameList = getShiftPatternNameList(latterHalfOfIndividualShiftList, shiftPatterns);


//        31日の月
        if (List.of(1, 3, 5, 7, 8, 10, 12).contains(nextMonth.getMonthValue()))
            this.shift = new LatterHalfOfDateListForm(shiftPatternNameList, 31);

//        30日の月
        if (List.of(4, 6, 9, 11).contains(nextMonth.getMonthValue()))
            this.shift = new LatterHalfOfDateListForm(shiftPatternNameList, 30);

//        2月
        if (nextMonth.getMonthValue() == 2) {
            //400で割り切れると閏年(29日)
            if (nextMonth.getYear() % 4 == 0 && nextMonth.getYear() % 400 == 0)
                this.shift = new LatterHalfOfDateListForm(shiftPatternNameList, 29);
            //100で割り切れると平年(28日)
            if (nextMonth.getYear() % 4 == 0 && nextMonth.getYear() % 100 == 0)
                this.shift = new LatterHalfOfDateListForm(shiftPatternNameList, 28);
            //4で割り切れると閏年(29日)
            if (nextMonth.getYear() % 4 == 0)
                this.shift = new LatterHalfOfDateListForm(shiftPatternNameList, 29);

            //それ以外は平年(28日)
            this.shift = new LatterHalfOfDateListForm(shiftPatternNameList, 28);
        }
    }
}
