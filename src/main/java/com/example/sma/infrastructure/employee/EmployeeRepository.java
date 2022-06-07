package com.example.sma.infrastructure.employee;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.Security;
import com.example.sma.domain.models.employee.WorkingForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeRepository {

//    従業員の全件取得
    public List<Employee> findAllEmployee();

//    勤務形態リストの取得
    public List<WorkingForm> findAllWorkingForm();

    public void registerForNewEmployee(Employee employee);

    public Employee findOneEmployee(int employeeId);

    public void updateEmployee(@Param("key") String key, @Param("value") String value, @Param("employeeId") int employeeId);

    public void deleteEmployee(int employeeId);
}
