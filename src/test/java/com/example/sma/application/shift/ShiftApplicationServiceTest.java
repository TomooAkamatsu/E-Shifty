package com.example.sma.application.shift;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.WorkingForm;
import com.example.sma.domain.models.shift.ShiftPattern;
import com.example.sma.domain.models.shift.VacationRequest;
import com.example.sma.infrastructure.shift.ShiftRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShiftApplicationServiceTest {

    @InjectMocks
    private ShiftApplicationService shiftApplicationService;

    @Mock
    private ShiftRepository shiftRepository;


    Employee[] employeeArr = {
            new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957/07/29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020/01/01", null, new WorkingForm(1, "正社員")),
            new Employee(2, "菅", "義偉", "Suga", "Yoshihide", "1948/12/06", 73, "男", "090-2222-2222", "suga@hoge.com", "2020/01/01", null, new WorkingForm(2, "正社員(時短)")),
            new Employee(3, "安倍", "晋三", "Abe", "Shinzo", "1954/09/21", 67, "男", "090-3333-3333", "abe@hoge.com", "2020/03/01", null, new WorkingForm(1, "正社員")),
            new Employee(4, "野田", "佳彦", "Noda", "Yoshihiko", "1957/05/20", 65, "男", "090-4444-4444", "noda@hoge.com", "2020/04/01", null, new WorkingForm(1, "正社員")),
            new Employee(5, "菅", "直人", "Kan", "Naoto", "1946/10/10", 75, "男", "090-5555-5555", "kan@hoge.com", "2020/05/01", null, new WorkingForm(3, "パート"))
    };
    ShiftPattern[] shiftPatternArr = {
            new ShiftPattern(1, "A", "0715", "1615"),
            new ShiftPattern(2, "B", "0800", "1700"),
            new ShiftPattern(3, "C", "0830", "1730"),
            new ShiftPattern(4, "D", "0800", "1700"),
            new ShiftPattern(5, "休み", null, null)
    };

    VacationRequest[] vacationRequestArr = {
            new VacationRequest(1,1,"2022/07/08"),
            new VacationRequest(2,1,"2022/07/19"),
            new VacationRequest(3,2,"2022/07/13"),
            new VacationRequest(4,2,"2022/07/14"),
            new VacationRequest(5,2,"2022/07/15"),
            new VacationRequest(6,4,"2022/07/28"),
            new VacationRequest(7,5,"2022/07/25"),
            new VacationRequest(8,5,"2022/07/26"),
            new VacationRequest(9,6,"2022/07/05"),
            new VacationRequest(10,6,"2022/07/12"),
            new VacationRequest(11,6,"2022/07/19"),
            new VacationRequest(12,7,"2022/07/01"),
            new VacationRequest(13,7,"2022/07/11"),
            new VacationRequest(14,8,"2022/07/06"),
            new VacationRequest(15,8,"2022/07/13"),
            new VacationRequest(16,8,"2022/07/20"),
            new VacationRequest(17,8,"2022/07/27"),
            new VacationRequest(18,9,"2022/07/15"),
            new VacationRequest(19,10,"2022/07/04"),
            new VacationRequest(20,10,"2022/07/05")
    };

//    @Test
//    void 翌月の日数確認(){
//        int actual = shiftApplicationService.createDraft(Arrays.asList(employeeArr),Arrays.asList(shiftPatternArr));
//        //来月が7月の場合
//        assertThat(actual).isEqualTo(30);
//    }

//    @Test
//    void 箱の確認() {
//        List<Shift> actualDraft = shiftApplicationService.createDraft(Arrays.asList(employeeArr), Arrays.asList(shiftPatternArr));
//        List<Integer> resultList = List.of(2, 3, 9, 10, 16, 17, 23, 24, 30, 31);
//
//        assertThat(actualDraft).hasSize(5 * 31);
//        assertThat(actualDraft.get(7).getShiftPatternId()).isEqualTo(5);
//        IntStream.rangeClosed(1, 155).forEach(i -> {
//            assertThat(actualDraft.get(i - 1).getEmployeeId()).isEqualTo((i - 1) / 31 + 1);
//
////            if (resultList.contains(i % 31))
////                assertThat(actualDraft.get(i - 1).getShiftPatternId()).isEqualTo(5);
////
////            if (!resultList.contains(i % 31))
////                assertThat(actualDraft.get(i - 1).getShiftPatternId()).isEqualTo(0);
//
//        });
//    }

    @Test
    void 土日の取得() {
        List<Integer> actualList = shiftApplicationService.getWeekend();
        assertThat(actualList).hasSize(10);

        List<Integer> resultList = List.of(2, 3, 9, 10, 16, 17, 23, 24, 30, 31);
        IntStream.range(0, 10).forEach(i -> {
            assertThat(actualList.get(i)).isEqualTo(resultList.get(i));
        });
    }

    @Test
    void 休み希望が全件取得(){
        when(shiftRepository.findVacationRequest(2022,7,1)).thenReturn(Arrays.asList(
                new VacationRequest(1,1,"2022-07-08"),
                new VacationRequest(2,1,"2022-07-19")
        ));
        List<VacationRequest> actualRequestList =
                shiftApplicationService.findVacationRequest(1);

        assertThat(actualRequestList.get(0).getRequestDate()).isEqualTo("2022-07-08");
    }

}
