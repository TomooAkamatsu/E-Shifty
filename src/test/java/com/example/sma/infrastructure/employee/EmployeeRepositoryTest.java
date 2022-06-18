package com.example.sma.infrastructure.employee;

import com.example.sma.domain.models.employee.WorkingForm;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@TestPropertySource(locations = "classpath:test.yml")
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void 従業員が全件取得できること() {
        List<WorkingForm> workingForms = employeeRepository.findAllWorkingForm();
        assertThat(workingForms)
                .hasSize(3)
                .contains(
                        new WorkingForm(0,"正社員"),
                        new WorkingForm(5,"正社員(時短)"),
                        new WorkingForm(4,"パート")
                );
    }

}
