package com.example.sma.infrastructure.shift;

import com.example.sma.domain.models.shift.Shift;
import com.example.sma.domain.models.shift.ShiftPattern;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShiftRepository {

    List<Shift> findShift(@Param("year") int year, @Param("month") int month, @Param("employeeId") int employeeId);

    List<ShiftPattern> findAllShiftPattern();
}
