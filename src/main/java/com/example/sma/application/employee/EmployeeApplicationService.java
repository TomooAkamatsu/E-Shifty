package com.example.sma.application.employee;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.Security;
import com.example.sma.domain.models.employee.WorkingForm;
import com.example.sma.exception.EmptyValueException;
import com.example.sma.exception.InvalidNumberException;
import com.example.sma.exception.NotFoundEmployeeException;
import com.example.sma.infrastructure.employee.EmployeeRepository;
import com.example.sma.presentation.employee.EmployeeOperationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeApplicationService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployee() {
        return employeeRepository.findAllEmployee();
    }

    public EmployeeOperationResult insertEmployee(Employee employee) {

        employeeRepository.insertEmployee(employee);

        return new EmployeeOperationResult(true, employee.getEmployeeId());
    }

    public EmployeeOperationResult updateEmployee(Map<String, String> patchDataMap, int employeeId) throws NotFoundEmployeeException {

        Employee targetEmployee = employeeRepository.findOneEmployee(employeeId)
                .orElseThrow(() -> new NotFoundEmployeeException("対象の従業員が見つかりませんでした"));

        if (patchDataMap.containsValue(null) || patchDataMap.containsValue("")) {
            throw new EmptyValueException("更新内容に空文字があります");
        }

        if (patchDataMap.containsKey("lastName")) {
            if (!patchDataMap.get("lastName").matches("^[^ -~｡-ﾟ]*$"))
                throw new ValidationException("姓を全角文字で入力してください");
            targetEmployee.setLastName(patchDataMap.get("lastName"));
        }

        if (patchDataMap.containsKey("firstName")) {
            if (!patchDataMap.get("firstName").matches("^[^ -~｡-ﾟ]*$"))
                throw new ValidationException("名を全角文字で入力してください");
            targetEmployee.setFirstName(patchDataMap.get("firstName"));
        }

        if (patchDataMap.containsKey("romanLastName")) {
            if (!patchDataMap.get("romanLastName").matches("^[a-zA-Z]*$"))
                throw new ValidationException("姓(ローマ字)を半角英字で入力してください");
            targetEmployee.setRomanLastName(patchDataMap.get("romanLastName"));
        }

        if (patchDataMap.containsKey("romanFirstName")) {
            if (!patchDataMap.get("romanFirstName").matches("^[a-zA-Z]*$"))
                throw new ValidationException("名(ローマ字)を半角英字で入力してください");
            targetEmployee.setRomanFirstName(patchDataMap.get("romanFirstName"));
        }

        if (patchDataMap.containsKey("birthday")) {
            if (!patchDataMap.get("birthday").matches("\\d{4}-\\d{1,2}-\\d{1,2}"))
                throw new ValidationException("誕生日を\"2022-01-01\"の形式で入力してください");
            targetEmployee.setBirthday(patchDataMap.get("birthday"));
        }

        if (patchDataMap.containsKey("age")) {
            if (!patchDataMap.get("age").matches("^\\d*$"))
                throw new ValidationException("年齢を半角数字で入力してください");
            int age = Integer.parseInt(patchDataMap.get("age"));
            if (age < 1 || age > 120) throw new InvalidNumberException("適正な範囲の数値を入力してください");
            targetEmployee.setAge(Integer.parseInt(patchDataMap.get("age")));
        }

        if (patchDataMap.containsKey("gender")) {
            if (!patchDataMap.get("gender").matches("^[^ -~｡-ﾟ]*$"))
                throw new ValidationException("入力された性別が正しくありません");
            targetEmployee.setGender(patchDataMap.get("gender"));
        }

        if (patchDataMap.containsKey("phoneNumber")) {
            if (!patchDataMap.get("phoneNumber").matches("^(\\d{3}-?\\d{4}-?\\d{4})?$"))
                throw new ValidationException("電話番号を\"000-0000-0000の形式で入力してください\"");
            targetEmployee.setPhoneNumber(patchDataMap.get("phoneNumber"));
        }

        if (patchDataMap.containsKey("email")) {
            if (!patchDataMap.get("email").matches("[\\w\\d_-]+@[\\w\\d._-]+"))
                throw new ValidationException("emailを\"hogehoge@huga.com\"の形式で入力してください");
            targetEmployee.setEmail(patchDataMap.get("email"));
        }

        if (patchDataMap.containsKey("employmentDate")) {
            if (!patchDataMap.get("employmentDate").matches("\\d{4}-\\d{1,2}-\\d{1,2}"))
                throw new ValidationException("雇用開始日を\"2022-01-01\"の形式で入力してください");
            targetEmployee.setEmploymentDate(patchDataMap.get("employmentDate"));
        }

        if (patchDataMap.containsKey("workingFormName")) {
            if (!patchDataMap.get("workingFormName").matches("^[^ -~｡-ﾟ]*$"))
                throw new ValidationException("入力された勤務形態が正しくありません");
            List<WorkingForm> workingFormList = employeeRepository.findAllWorkingForm();
            workingFormList.stream()
                    .filter(workingForm -> patchDataMap.get("workingFormName").equals(workingForm.getWorkingFormName()))
                    .forEach(targetEmployee::setWorkingForms);
        }

        employeeRepository.updateEmployee(targetEmployee);

        return new EmployeeOperationResult(true, employeeId);
    }

    public EmployeeOperationResult deleteEmployee(int employeeId) {
        employeeRepository.deleteEmployee(employeeId);
        return new EmployeeOperationResult(true , employeeId);
    }

    public List<Integer> getEmployeeIdList() {
        return employeeRepository.findAllEmployee().stream().map(Employee::getEmployeeId).toList();
    }

    public List<WorkingForm> findAllWorkingForm() {
        return employeeRepository.findAllWorkingForm();
    }

    public void reset() {
        employeeRepository.deleteAllEmployee();
        employeeRepository.alterTableEmployee();
        employeeRepository.insertAllEmployee();
    }

    public Security getLoginInfo(int employeeId) {
//        todo:null時の処理
        return employeeRepository.getLoginInfo(employeeId).orElseThrow();
    }
}
