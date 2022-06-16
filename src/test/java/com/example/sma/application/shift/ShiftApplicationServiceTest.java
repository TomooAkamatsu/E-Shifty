package com.example.sma.application.shift;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.WorkingForm;
import com.example.sma.domain.models.shift.ShiftPattern;
import com.example.sma.infrastructure.employee.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ShiftApplicationServiceTest {

    @InjectMocks
    private ShiftApplicationService shiftApplicationService;

    @Mock
    private EmployeeRepository employeeRepository;


    Employee[] employeeArr = {
            new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957/07/29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020/01/01", null, new WorkingForm(1, "正社員")),
            new Employee(2, "菅", "義偉", "Suga", "Yoshihide", "1948/12/06", 73, "男", "090-2222-2222", "suga@hoge.com", "2020/01/01", null, new WorkingForm(2, "正社員(時短)")),
            new Employee(3, "安倍", "晋三", "Abe", "Shinzo", "1954/09/21", 67, "男", "090-3333-3333", "abe@hoge.com", "2020/03/01", null, new WorkingForm(1, "正社員")),
            new Employee(4, "野田", "佳彦", "Noda", "Yoshihiko", "1957/05/20", 65, "男", "090-4444-4444", "noda@hoge.com", "2020/04/01", null, new WorkingForm(1, "正社員")),
            new Employee(5, "菅", "直人", "Kan", "Naoto", "1946/10/10", 75, "男", "090-5555-5555", "kan@hoge.com", "2020/05/01", null, new WorkingForm(3, "パート"))
    };
    ShiftPattern[] shiftPatternArr = {
            new ShiftPattern(1, "A","0715","1615"),
            new ShiftPattern(2, "B","0800","1700"),
            new ShiftPattern(3, "C","0830","1730"),
            new ShiftPattern(4, "D","0800","1700"),
            new ShiftPattern(5, "休み",null,null)
    };

//    @Test
//    void 翌月の日数確認(){
//        int actual = shiftApplicationService.createDraft(Arrays.asList(employeeArr),Arrays.asList(shiftPatternArr));
//        //来月が7月の場合
//        assertThat(actual).isEqualTo(30);
//    }

    @Test
    void 箱の確認(){
        List<List<String>> actualDraft = shiftApplicationService.createDraft(Arrays.asList(employeeArr),Arrays.asList(shiftPatternArr));
        assertThat(actualDraft).hasSize(5);
        assertThat(actualDraft.get(0)).hasSize(30);
    }

}
