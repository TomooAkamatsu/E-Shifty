package com.example.sma.presentation.shift;

import com.example.sma.domain.models.shift.Shift;
import com.example.sma.domain.models.shift.ShiftPattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DraftForm {
    private FirstHalfOfDraftForm[] firstHalf;
    private LatterHalfOfDraftForm[] latterHalf;

    public DraftForm(List<List<Shift>> shiftList, List<String> employeeNameList, List<ShiftPattern> shiftPatterns) {
        List<FirstHalfOfDraftForm> listForFirstHalf = new ArrayList<>();
        shiftList.forEach(individualShiftList -> listForFirstHalf.add(new FirstHalfOfDraftForm(individualShiftList, employeeNameList, shiftPatterns)));
        this.firstHalf = listForFirstHalf.toArray(new FirstHalfOfDraftForm[employeeNameList.size()]);

        List<LatterHalfOfDraftForm> listForLatterHalf = new ArrayList<>();
        shiftList.forEach(individualShiftList -> listForLatterHalf.add(new LatterHalfOfDraftForm(individualShiftList, employeeNameList, shiftPatterns)));
        this.latterHalf = listForLatterHalf.toArray(new LatterHalfOfDraftForm[employeeNameList.size()]);
    }
}
