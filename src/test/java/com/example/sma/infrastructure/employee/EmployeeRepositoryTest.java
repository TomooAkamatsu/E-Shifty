package com.example.sma.infrastructure.employee;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.WorkingForm;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@MybatisTest
@TestPropertySource(locations = "classpath:test.yml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Order(1)
    public void 勤務形態を全件取得できること() {
        List<WorkingForm> actualWorkingForms = employeeRepository.findAllWorkingForm();
        assertThat(actualWorkingForms)
                .hasSize(3)
                .contains(
                        new WorkingForm(1, "正社員"),
                        new WorkingForm(2, "正社員(時短)"),
                        new WorkingForm(3, "パート")
                );
    }

    @Test
    @Order(2)
    public void 従業員を全件取得できること() {
        List<Employee> actualEmployeeList = employeeRepository.findAllEmployee();
        assertThat(actualEmployeeList)
                .hasSize(5)
                .contains(
                        new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957-07-29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020-01-01", null, new WorkingForm(1, "正社員")),
                        new Employee(2, "菅", "義偉", "Suga", "Yoshihide", "1948-12-06", 73, "男", "090-2222-2222", "suga@hoge.com", "2020-02-01", null, new WorkingForm(2, "正社員(時短)")),
                        new Employee(3, "安倍", "晋三", "Abe", "Shinzo", "1954-09-21", 67, "男", "090-3333-3333", "abe@hoge.com", "2020-03-01", null, new WorkingForm(1, "正社員")),
                        new Employee(4, "野田", "佳彦", "Noda", "Yoshihiko", "1957-05-20", 65, "男", "090-4444-4444", "noda@hoge.com", "2020-04-01", null, new WorkingForm(1, "正社員")),
                        new Employee(5, "菅", "直人", "Kan", "Naoto", "1946-10-10", 75, "男", "090-5555-5555", "kan@hoge.com", "2020-05-01", null, new WorkingForm(3, "パート"))
                );
    }

    //    なぜかH2でAUTO_INCREMENTであるemployee_idをINSERTしているとAUTO_INCREMENTが機能しない... (MySQLでは問題なく動く)
    @Test
    @Order(3)
    public void 従業員IDを採番処理して従業員の新規登録ができること() throws Exception {
        Employee newEmployee = new Employee("鳩山", "由紀夫", "Hatoyama", "Yukio", "1947-02-11", 75, "男", "090-6666-6666", "hatoyama@hoge.com", "2020-06-01", null, new WorkingForm(1, "正社員"));
        employeeRepository.insertEmployee(newEmployee);

        Employee actualEmployee = employeeRepository.findOneEmployee(6).orElse(new Employee());
        assertThat(actualEmployee).isEqualTo(
                new Employee(6, "鳩山", "由紀夫", "Hatoyama", "Yukio", "1947-02-11", 75, "男", "090-6666-6666", "hatoyama@hoge.com", "2020-06-01", null, new WorkingForm(1, "正社員"))
        ).isNotEqualTo(new Employee());
    }

    //    このあたりはフォームクラスのバリデーションにて制御予定
    @Test
    @Order(4)
    public void NOTNULL制約がある項目にNULLを渡して新規登録するとDataIntegrityViolationExceptionとなること() throws Exception {
        Employee newEmployee = new Employee(null, null, "Hatoyama", "Yukio", "1947-02-11", 75, "男", "090-6666-6666", "hatoyama@hoge.com", "2020-06-01", null, new WorkingForm(1, "正社員"));
        assertThatThrownBy(() -> {
            employeeRepository.insertEmployee(newEmployee);
        }).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @Order(5)
    public void 従業員idに紐づく1件の更新ができること() {
        employeeRepository.updateEmployee(
                new Employee(1, "赤松", "知音", "Akamatsu", "TOmoo", "1995-08-11", 26, "男", "090-0000-0000", "akmt@hoge.com", "2020-01-01", null, new WorkingForm(1, "正社員"))
        );

        Employee actualEmployee = employeeRepository.findOneEmployee(1).orElse(new Employee());

        assertThat(actualEmployee)
                .isEqualTo(
                        new Employee(1, "赤松", "知音", "Akamatsu", "TOmoo", "1995-08-11", 26, "男", "090-0000-0000", "akmt@hoge.com", "2020-01-01", null, new WorkingForm(1, "正社員"))
                ).isNotEqualTo(new Employee());
    }

    //       このケースはフォームクラスを介さないのでここから例外を投げて処理するかフロントで制御したい
    @Test
    @Order(6)
    public void NOTNULL制約の項目にNULLを渡して従業員idに紐づく1件の更新を行うとDataIntegrityViolationExceptionとなること() {
        assertThatThrownBy(() -> {
            employeeRepository.updateEmployee(
                    new Employee(1, null, "文雄", null, "Fumio", "1957-07-29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020-01-01", null, new WorkingForm(1, "正社員"))
            );
        }).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @Order(7)
    public void 従業員IDに紐づく1件の削除ができること() {
        List<Employee> actualEmployeeList = employeeRepository.findAllEmployee();
        assertThat(actualEmployeeList)
                .hasSize(5)
                .contains(
                        new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957-07-29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020-01-01", null, new WorkingForm(1, "正社員")),
                        new Employee(2, "菅", "義偉", "Suga", "Yoshihide", "1948-12-06", 73, "男", "090-2222-2222", "suga@hoge.com", "2020-02-01", null, new WorkingForm(2, "正社員(時短)")),
                        new Employee(3, "安倍", "晋三", "Abe", "Shinzo", "1954-09-21", 67, "男", "090-3333-3333", "abe@hoge.com", "2020-03-01", null, new WorkingForm(1, "正社員")),
                        new Employee(4, "野田", "佳彦", "Noda", "Yoshihiko", "1957-05-20", 65, "男", "090-4444-4444", "noda@hoge.com", "2020-04-01", null, new WorkingForm(1, "正社員")),
                        new Employee(5, "菅", "直人", "Kan", "Naoto", "1946-10-10", 75, "男", "090-5555-5555", "kan@hoge.com", "2020-05-01", null, new WorkingForm(3, "パート"))
                );

        employeeRepository.deleteEmployee(1);
        List<Employee> actualEmployeeListAfterDelete = employeeRepository.findAllEmployee();
        assertThat(actualEmployeeListAfterDelete)
                .hasSize(4)
                .contains(
                        new Employee(2, "菅", "義偉", "Suga", "Yoshihide", "1948-12-06", 73, "男", "090-2222-2222", "suga@hoge.com", "2020-02-01", null, new WorkingForm(2, "正社員(時短)")),
                        new Employee(3, "安倍", "晋三", "Abe", "Shinzo", "1954-09-21", 67, "男", "090-3333-3333", "abe@hoge.com", "2020-03-01", null, new WorkingForm(1, "正社員")),
                        new Employee(4, "野田", "佳彦", "Noda", "Yoshihiko", "1957-05-20", 65, "男", "090-4444-4444", "noda@hoge.com", "2020-04-01", null, new WorkingForm(1, "正社員")),
                        new Employee(5, "菅", "直人", "Kan", "Naoto", "1946-10-10", 75, "男", "090-5555-5555", "kan@hoge.com", "2020-05-01", null, new WorkingForm(3, "パート"))
                )
                .doesNotContain(
                        new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957-07-29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020-01-01", null, new WorkingForm(1, "正社員"))
                );
    }

    @Test
    @Order(8)
    public void 存在しない従業員IDに紐づく1件を削除しようとしても何も起こらないこと() {
        List<Employee> actualEmployeeList = employeeRepository.findAllEmployee();
        assertThat(actualEmployeeList).hasSize(5);

        employeeRepository.deleteEmployee(6);

        List<Employee> actualEmployeeListAfterDelete = employeeRepository.findAllEmployee();
        assertThat(actualEmployeeListAfterDelete).hasSize(5);
    }

    @Test
    @Order(9)
    public void 従業員IDに紐づく一件が検索できること() {
        Employee actualEmployee = employeeRepository.findOneEmployee(1).orElseThrow();
        assertThat(actualEmployee).isEqualTo(
                new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957-07-29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020-01-01", null, new WorkingForm(1, "正社員"))
        );
    }

    //    そもそも存在しない従業員IDが渡されることがないため、Optionalにする必要があるのか
    //    Optionalを使用する場合、空だった場合のテストはどれが適切なのか
    @Test
    @Order(10)
    public void 存在しない従業員IDに紐づく一件を検索するとNULLが返り処理されること() {
        Employee actualEmployee = employeeRepository.findOneEmployee(10).orElse(new Employee());
        assertThat(actualEmployee).isEqualTo(new Employee());
        assertThatThrownBy(() -> {
            employeeRepository.findOneEmployee(10).orElseThrow();
        }).isInstanceOf(NoSuchElementException.class);
    }

}
