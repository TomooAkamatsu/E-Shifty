package com.example.sma.infrastructure.employee;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.Security;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeRepository {

//    従業員の全件取得
//    @Select("SELECT * FROM employee")
    public List<Employee> findAllEmployee();

//    @Select("SELECT * FROM security")
    public List<Security> findAllSecurity();
}
