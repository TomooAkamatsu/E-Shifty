package com.example.sma.infrastructure.employee;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.WorkingForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeRepository {

//    従業員の全件取得
    public List<Employee> findAllEmployee();

//    従業員を新規登録
    public void insertEmployee(Employee employee);

//    従業員情報を更新
    public void updateEmployee(@Param("key") String key, @Param("value") String value, @Param("employeeId") int employeeId);

//    従業員を削除
    public void deleteEmployee(int employeeId);

//    勤務形態の全件取得
    public List<WorkingForm> findAllWorkingForm();

}
