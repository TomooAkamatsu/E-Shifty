package com.example.sma.infrastructure.employee;

import com.example.sma.domain.models.employee.WorkingForm;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


//@TestExecutionListeners({
//        DependencyInjectionTestExecutionListener.class,
//        DirtiesContextTestExecutionListener.class,
//        TransactionalTestExecutionListener.class,
//        DbUnitTestExecutionListener.class
//})
//@TestPropertySource(locations = "classpath:test.yml")
//@MybatisTest
@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DatabaseSetup("/dbunit/workingForm.xml")
    public void 従業員が全件取得できること() {
        List<WorkingForm> workingForms = employeeRepository.findAllWorkingForm();
        assertThat(workingForms)
                .hasSize(3)
                .contains(
                        new WorkingForm(1,"正社員"),
                        new WorkingForm(2,"正社員(時短)"),
                        new WorkingForm(3,"パート")
                        );
    }

}
