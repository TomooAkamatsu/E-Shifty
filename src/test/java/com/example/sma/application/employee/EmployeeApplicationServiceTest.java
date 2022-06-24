package com.example.sma.application.employee;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.WorkingForm;
import com.example.sma.exception.EmptyValueException;
import com.example.sma.exception.NotFoundEmployeeException;
import com.example.sma.infrastructure.employee.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ValidationException;
import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    void 従業員の新規登録に成功したら完了メッセージが返ること() {
        Employee newEmployee = new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957/07/29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020/01/01", null, new WorkingForm(1, "正社員"));
        String actual = employeeApplicationService.insertEmployee(newEmployee);
        assertThat(actual).isEqualTo("{\"insertionCompleted\":\"1\"}");
    }

//    DTOにバリデーションを追加、その上ExceptionHandlerにて処理するため新規登録失敗時のエラーは必要ない？
//    @Test
//    void 従業員の新規登録時にエラーをcatchしたらfalseが返ること() {
//        Employee newEmployee = new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957/07/29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020/01/01", null, new WorkingForm(1, "正社員"));
//        doThrow(new BindingException()).when(employeeRepository).insertEmployee(newEmployee);
//        String actual = employeeApplicationService.insertEmployee(newEmployee);
//        assertThat(actual).isEqualTo(false);
//    }

    @Test
    void 従業員情報の更新に成功したら完了メッセージが返ること() {
        when(employeeRepository.findOneEmployee(1)).thenReturn(
                Optional.of(new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957/07/29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020/01/01", null, new WorkingForm(1, "正社員")))
        );
        Map<String, String> patchDataMap = new HashMap<>() {{
            put("lastName", "赤松");
            put("FirstName", "知音");
        }};
        String actual = employeeApplicationService.updateEmployee(patchDataMap, 1);
        assertThat(actual).isEqualTo("{\"updatingCompleted\":\"1\"}");
    }

    @Test
    void 従業員情報の更新時に対象従業員の情報が見つからなかった場合NotFoundEmployeeExceptionになること() {
        when(employeeRepository.findOneEmployee(1)).thenReturn(Optional.empty());
        Map<String, String> patchDataMap = new HashMap<>() {{
            put("lastName", "赤松");
            put("FirstName", "知音");
        }};
        assertThatThrownBy(() -> {
            employeeApplicationService.updateEmployee(patchDataMap, 1);
        }).isInstanceOf(NotFoundEmployeeException.class);
    }

    @Test
    void 従業員情報の更新時に空文字が送信されたらEmptyValueExceptionになること() {
        when(employeeRepository.findOneEmployee(1)).thenReturn(
                Optional.of(new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957/07/29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020/01/01", null, new WorkingForm(1, "正社員")))
        );
        Map<String, String> patchDataMap = new HashMap<>() {{
            put("lastName", "");
            put("FirstName", "");
        }};
        assertThatThrownBy(() -> {
            employeeApplicationService.updateEmployee(patchDataMap, 1);
        }).isInstanceOf(EmptyValueException.class);
    }

    @Test
    void 従業員情報の更新時に不正な内容が送信されたらValidationExceptionとなること() {
        when(employeeRepository.findOneEmployee(1)).thenReturn(
                Optional.of(new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957/07/29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020/01/01", null, new WorkingForm(1, "正社員")))
        );
        Map<String, String> patchDataMap = new HashMap<>() {{
            put("lastName", "hoge");
        }};
        assertThatThrownBy(() -> {
            employeeApplicationService.updateEmployee(patchDataMap, 1);
        }).isInstanceOf(ValidationException.class);
    }

    @Test
    void 従業員情報を削除に成功したら完了メッセージが返ること() {
        String actual = employeeApplicationService.deleteEmployee(1);
        assertThat(actual).isEqualTo("{\"deletingCompleted\":\"1\"}");
    }

//    ExceptionHandlerにて対応するので削除失敗時のテストケースは必要ない？
//    @Test
//    void 従業員情報を削除時にエラーをcatchしたらfalseが返ること() {
//        doThrow(new BindingException()).when(employeeRepository).deleteEmployee(1);
//        String actual = employeeApplicationService.deleteEmployee(1);
//        assertThat(actual).isEqualTo(false);
//    }

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
