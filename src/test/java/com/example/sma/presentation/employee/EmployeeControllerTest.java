package com.example.sma.presentation.employee;

import com.example.sma.application.employee.EmployeeApplicationService;
import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.WorkingForm;
import com.example.sma.exception.EmptyValueException;
import com.example.sma.exception.InvalidNumberException;
import com.example.sma.exception.NotFoundEmployeeException;
import com.example.sma.presentation.exceptionHandler.GlobalExceptionHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    //    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeApplicationService employeeApplicationService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
                .setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    Map<String, String> convertToJson(String data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(data, new TypeReference<>() {
        });
    }

    @Test
    public void 勤務形態が全件取得に成功すると200で内容を返すこと() throws Exception {
        when(employeeApplicationService.findAllWorkingForm()).thenReturn(Arrays.asList(
                new WorkingForm(1, "正社員"),
                new WorkingForm(2, "正社員(時短)"),
                new WorkingForm(3, "パート")
        ));

        mockMvc.perform(get("/api/employees/working-form")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"workingFormId\":1,\"workingFormName\":\"正社員\"},{\"workingFormId\":2,\"workingFormName\":\"正社員(時短)\"},{\"workingFormId\":3,\"workingFormName\":\"パート\"}]"));
    }

    @Test
    void 従業員情報を全件取得に成功すると200で内容を返すこと() throws Exception {
        when(employeeApplicationService.findAllEmployee()).thenReturn(Arrays.asList(
                new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957-07-29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020-01-01", null, new WorkingForm(1, "正社員")),
                new Employee(2, "菅", "義偉", "Suga", "Yoshihide", "1948-12-06", 73, "男", "090-2222-2222", "suga@hoge.com", "2020-02-01", null, new WorkingForm(2, "正社員(時短)")),
                new Employee(3, "安倍", "晋三", "Abe", "Shinzo", "1954-09-21", 67, "男", "090-3333-3333", "abe@hoge.com", "2020-03-01", null, new WorkingForm(1, "正社員"))
        ));

        mockMvc.perform(get("/api/employees")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"employeeId\":1,\"lastName\":\"岸田\",\"firstName\":\"文雄\",\"romanLastName\":\"Kishida\",\"romanFirstName\":\"Fumio\",\"birthday\":\"1957-07-29\",\"age\":64,\"gender\":\"男\",\"phoneNumber\":\"090-1111-1111\",\"email\":\"kishida@hoge.com\",\"employmentDate\":\"2020-01-01\",\"workingFormName\":\"正社員\"}," +
                        "{\"employeeId\":3,\"lastName\":\"安倍\",\"firstName\":\"晋三\",\"romanLastName\":\"Abe\",\"romanFirstName\":\"Shinzo\",\"birthday\":\"1954-09-21\",\"age\":67,\"gender\":\"男\",\"phoneNumber\":\"090-3333-3333\",\"email\":\"abe@hoge.com\",\"employmentDate\":\"2020-03-01\",\"workingFormName\":\"正社員\"}," +
                        "{\"employeeId\":2,\"lastName\":\"菅\",\"firstName\":\"義偉\",\"romanLastName\":\"Suga\",\"romanFirstName\":\"Yoshihide\",\"birthday\":\"1948-12-06\",\"age\":73,\"gender\":\"男\",\"phoneNumber\":\"090-2222-2222\",\"email\":\"suga@hoge.com\",\"employmentDate\":\"2020-02-01\",\"workingFormName\":\"正社員(時短)\"}]"));
    }

    @Test
    void 従業員の新規登録に成功したら201と完了メッセージが返ること() throws Exception {
        EmployeeForm employeeForm = new EmployeeForm(new Employee(1, "岸田", "文雄", "Kishida", "Fumio", "1957-07-29", 64, "男", "090-1111-1111", "kishida@hoge.com", "2020-01-01", null, new WorkingForm(1, "正社員")));
        when(employeeApplicationService.insertEmployee(employeeForm.convertToEntity()))
                .thenReturn("{\"insertionCompleted\":\"1\"}");

        mockMvc.perform(post("/api/employees")
                        .content("{\"lastName\":\"岸田\",\"firstName\":\"文雄\",\"romanLastName\":\"Kishida\",\"romanFirstName\":\"Fumio\",\"birthday\":\"1957-07-29\",\"age\":64,\"gender\":\"男\",\"phoneNumber\":\"090-1111-1111\",\"email\":\"kishida@hoge.com\",\"employmentDate\":\"2020-01-01\",\"workingFormName\":\"正社員\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andExpect(content().string("{\"insertionCompleted\":\"1\"}"));
    }

    @Test
    void 不正な内容で従業員の新規登録をすると失敗して400とMethodArgumentNotValidExceptionが返ること() throws Exception {
        mockMvc.perform(post("/api/employees")
                        .content("{\"employeeId\":1,\"lastName\":\"\",\"firstName\":\"\",\"romanLastName\":\"Kishida\",\"romanFirstName\":\"Fumio\",\"birthday\":\"1957-07-29\",\"age\":64,\"gender\":\"男\",\"phoneNumber\":\"090-1111-1111\",\"email\":\"kishida@hoge.com\",\"employmentDate\":\"2020-01-01\",\"workingFormName\":\"正社員\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"exception\":\"org.springframework.web.bind.MethodArgumentNotValidException\",\"message\":\"不正な登録内容です\",\"status\":\"BAD_REQUEST\"}"));
    }

    @Test
    void 従業員の情報更新に成功すると200と完了メッセージが返ること() throws Exception {
        String patchData = "{\"lastName\":\"赤松\",\"age\":\"60\"}";

        when(employeeApplicationService.updateEmployee(convertToJson(patchData), 1)).thenReturn("{\"updatingCompleted\":\"1\"}");

        mockMvc.perform(patch("/api/employees/1")
                        .content(patchData)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"updatingCompleted\":\"1\"}"));
    }

    @Test
    void InvalidNumberExceptionが発生すると400とそのメッセージが返ること() throws Exception {
        String patchData = "{\"lastName\":\"赤松\",\"age\":\"126\"}";

        when(employeeApplicationService.updateEmployee(convertToJson(patchData), 1)).thenThrow(new InvalidNumberException("hoge"));

        mockMvc.perform(patch("/api/employees/1")
                        .content(patchData)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(content().string("{\"exception\":\"com.example.sma.exception.InvalidNumberException\",\"message\":\"hoge\",\"status\":\"BAD_REQUEST\"}"));
    }

    @Test
    void NotFoundEmployeeExceptionが発生すると400とそのメッセージが返ること() throws Exception {
        String patchData = "{\"lastName\":\"赤松\",\"age\":\"26\"}";

        when(employeeApplicationService.updateEmployee(convertToJson(patchData), 10)).thenThrow(new NotFoundEmployeeException("hoge"));

        mockMvc.perform(patch("/api/employees/10")
                        .content(patchData)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(content().string("{\"exception\":\"com.example.sma.exception.NotFoundEmployeeException\",\"message\":\"hoge\",\"status\":\"BAD_REQUEST\"}"));
    }

    @Test
    void EmptyValueExceptionが発生すると400とそのメッセージが返ること() throws Exception {
        String patchData = "{\"lastName\":\"\",\"\":\"26\"}";

        when(employeeApplicationService.updateEmployee(convertToJson(patchData), 1)).thenThrow(new EmptyValueException("hoge"));

        mockMvc.perform(patch("/api/employees/1")
                        .content(patchData)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(content().string("{\"exception\":\"com.example.sma.exception.EmptyValueException\",\"message\":\"hoge\",\"status\":\"BAD_REQUEST\"}"));
    }

    @Test
    void 従業員の削除に成功すると200と完了メッセージが返ること() throws Exception {
        when(employeeApplicationService.deleteEmployee(1)).thenReturn("{\"deletingCompleted\":\"1\"}");

        mockMvc.perform(delete("/api/employees/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"deletingCompleted\":\"1\"}"));
    }

}
