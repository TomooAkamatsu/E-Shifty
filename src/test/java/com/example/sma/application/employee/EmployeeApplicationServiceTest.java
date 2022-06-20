package com.example.sma.application.employee;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.WorkingForm;
import com.example.sma.infrastructure.employee.EmployeeRepository;
import org.apache.ibatis.binding.BindingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeApplicationServiceTest {

    @InjectMocks
    private EmployeeApplicationService employeeApplicationService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void 従業員情報を全件取得してそのまま返すこと() {
        Employee[] employeeArr = {
                new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957/07/29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020/01/01", null, new WorkingForm(1, "正社員")),
                new Employee(2, "菅", "義偉", "Suga", "Yoshihide", "1948/12/06", 73, "男", "090-2222-2222", "suga@hoge.com", "2020/01/01", null, new WorkingForm(2, "正社員(時短)")),
                new Employee(3, "安倍", "晋三", "Abe", "Shinzo", "1954/09/21", 67, "男", "090-3333-3333", "abe@hoge.com", "2020/03/01", null, new WorkingForm(1, "正社員")),
                new Employee(4, "野田", "佳彦", "Noda", "Yoshihiko", "1957/05/20", 65, "男", "090-4444-4444", "noda@hoge.com", "2020/04/01", null, new WorkingForm(1, "正社員")),
                new Employee(5, "菅", "直人", "Kan", "Naoto", "1946/10/10", 75, "男", "090-5555-5555", "kan@hoge.com", "2020/05/01", null, new WorkingForm(3, "パート"))
        };
        when(employeeRepository.findAllEmployee()).thenReturn(Arrays.asList(employeeArr));
        List<Employee> actualEmployees = employeeApplicationService.findAllEmployee();
        assertThat(actualEmployees).hasSize(5);

        IntStream.range(0, actualEmployees.size()).forEach(i -> {
            assertThat(actualEmployees.get(i)).isEqualTo(employeeArr[i]);
            assertThat(actualEmployees.get(i)).isInstanceOf(Employee.class);
        });
    }

    @Test
    void 従業員を新規登録に成功したらtrueが返ること() {
        Employee newEmployee = new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957/07/29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020/01/01", null, new WorkingForm(1, "正社員"));
        boolean actual = employeeApplicationService.insertEmployee(newEmployee);
        assertThat(actual).isEqualTo(true);
    }

    @Test
    void 従業員の新規登録時にエラーをcatchしたらfalseが返ること() {
        Employee newEmployee = new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957/07/29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020/01/01", null, new WorkingForm(1, "正社員"));
        doThrow(new BindingException()).when(employeeRepository).insertEmployee(newEmployee);
        boolean actual = employeeApplicationService.insertEmployee(newEmployee);
        assertThat(actual).isEqualTo(false);
    }

    @Test
    void 従業員情報の更新に成功したらtrueが返ること() {
        when(employeeRepository.findOneEmployee(1)).thenReturn(
                Optional.of(new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957/07/29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020/01/01", null, new WorkingForm(1, "正社員")))
        );
        Map<String, String> patchDataMap = new HashMap<>() {{
            put("lastName", "赤松");
            put("FirstName", "知音");
        }};
        boolean actual = employeeApplicationService.updateEmployee(patchDataMap, 1);
        assertThat(actual).isEqualTo(true);
    }

    @Test
    void 従業員情報の更新時にエラーをcatchしたらfalseが返ること() {
        when(employeeRepository.findOneEmployee(1)).thenReturn(
                Optional.of(new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957/07/29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020/01/01", null, new WorkingForm(1, "正社員")))
        );
        doThrow(new BindingException()).when(employeeRepository).updateEmployee(
                new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957/07/29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020/01/01", null, new WorkingForm(1, "正社員"))
        );
        Map<String, String> patchDataMap = new HashMap<>() {{
            put("lastName", "赤松");
            put("FirstName", "知音");
        }};
        boolean actual = employeeApplicationService.updateEmployee(patchDataMap, 1);
        assertThat(actual).isEqualTo(false);
    }

    @Test
    void 従業員情報を削除に成功したらtrueが返ること() {
        boolean actual = employeeApplicationService.deleteEmployee(1);
        assertThat(actual).isEqualTo(true);
    }

    @Test
    void 従業員情報を削除時にエラーをcatchしたらfalseが返ること() {
        doThrow(new BindingException()).when(employeeRepository).deleteEmployee(1);
        boolean actual = employeeApplicationService.deleteEmployee(1);
        assertThat(actual).isEqualTo(false);

    }

    @Test
    void 全従業員のIDリストをint型で返すことができること() {
        when(employeeRepository.findAllEmployee()).thenReturn(Arrays.asList(
                new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957/07/29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020/01/01", null, new WorkingForm(1, "正社員")),
                new Employee(2, "菅", "義偉", "Suga", "Yoshihide", "1948/12/06", 73, "男", "090-2222-2222", "suga@hoge.com", "2020/01/01", null, new WorkingForm(2, "正社員(時短)")),
                new Employee(3, "安倍", "晋三", "Abe", "Shinzo", "1954/09/21", 67, "男", "090-3333-3333", "abe@hoge.com", "2020/03/01", null, new WorkingForm(1, "正社員")),
                new Employee(4, "野田", "佳彦", "Noda", "Yoshihiko", "1957/05/20", 65, "男", "090-4444-4444", "noda@hoge.com", "2020/04/01", null, new WorkingForm(1, "正社員")),
                new Employee(5, "菅", "直人", "Kan", "Naoto", "1946/10/10", 75, "男", "090-5555-5555", "kan@hoge.com", "2020/05/01", null, new WorkingForm(3, "パート"))
        ));
        List<Integer> actualEmployeeIdList = employeeApplicationService.getEmployeeIdList();
        assertThat(actualEmployeeIdList).hasSize(5);

        IntStream.range(0, actualEmployeeIdList.size()).forEach(i ->
                assertThat(actualEmployeeIdList.get(i)).isInstanceOf(Integer.class)
        );
    }


    @Test
    void 全従業員の苗字リストをString型で返すことができること() {
        when(employeeRepository.findAllEmployee()).thenReturn(Arrays.asList(
                new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957/07/29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020/01/01", null, new WorkingForm(1, "正社員")),
                new Employee(2, "菅", "義偉", "Suga", "Yoshihide", "1948/12/06", 73, "男", "090-2222-2222", "suga@hoge.com", "2020/01/01", null, new WorkingForm(2, "正社員(時短)")),
                new Employee(3, "安倍", "晋三", "Abe", "Shinzo", "1954/09/21", 67, "男", "090-3333-3333", "abe@hoge.com", "2020/03/01", null, new WorkingForm(1, "正社員")),
                new Employee(4, "野田", "佳彦", "Noda", "Yoshihiko", "1957/05/20", 65, "男", "090-4444-4444", "noda@hoge.com", "2020/04/01", null, new WorkingForm(1, "正社員")),
                new Employee(5, "菅", "直人", "Kan", "Naoto", "1946/10/10", 75, "男", "090-5555-5555", "kan@hoge.com", "2020/05/01", null, new WorkingForm(3, "パート"))
        ));
        List<String> actualEmployeeNameList = employeeApplicationService.getEmployeeNameList();
        assertThat(actualEmployeeNameList).hasSize(5);

        IntStream.range(0, actualEmployeeNameList.size()).forEach(i ->
                assertThat(actualEmployeeNameList.get(i)).isInstanceOf(String.class)
        );
    }

    @Test
    void 勤務形態を全件取得してそのまま返すこと() {
        WorkingForm[] workingFormArr = {
                new WorkingForm(1, "正社員"),
                new WorkingForm(2, "正社員(時短)"),
                new WorkingForm(3, "パート")};
        when(employeeRepository.findAllWorkingForm()).thenReturn(Arrays.asList(workingFormArr));
        List<WorkingForm> actualWorkingForms = employeeApplicationService.findAllWorkingForm();

        assertThat(actualWorkingForms).hasSize(3);
        IntStream.range(0, actualWorkingForms.size()).forEach(i -> {
            assertThat(actualWorkingForms.get(i)).isEqualTo(workingFormArr[i]);
            assertThat(actualWorkingForms.get(i)).isInstanceOf(WorkingForm.class);
        });
    }
}
