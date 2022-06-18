package com.example.sma.presentation.employee;

import com.example.sma.application.employee.EmployeeApplicationService;
import com.example.sma.domain.models.employee.WorkingForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeApplicationService employeeApplicationService;

    @Test
    public void 勤務形態が全件取得できること() throws Exception {
        when(employeeApplicationService.findAllWorkingForm()).thenReturn(Arrays.asList(
                new WorkingForm(1,"正社員"),
                new WorkingForm(2, "正社員(時短)"),
                new WorkingForm(3, "パート")
        ));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/employees/working-for")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"workingFormId\":1,\"workingFormName\":\"正社員\"},{\"workingFormId\":2,\"workingFormName\":\"正社員(時短)\"},{\"workingFormId\":3,\"workingFormName\":\"パート\"}]"))
                ;

    }


}
