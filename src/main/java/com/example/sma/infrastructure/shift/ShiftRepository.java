package com.example.sma.infrastructure.shift;

import com.example.sma.domain.models.shift.Shift;
import com.example.sma.domain.models.shift.ShiftPattern;
import com.example.sma.domain.models.shift.VacationRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShiftRepository {

    List<Shift> findIndividualMonthlyShift(@Param("year") int year, @Param("month") int month, @Param("employeeId") int employeeId);

    List<ShiftPattern> findAllShiftPattern();

    List<VacationRequest> findVacationRequest(@Param("year") int year, @Param("month") int month, @Param("employeeId") int employeeId);

    void insertVacationRequest(@Param("employeeId") int employeeId, @Param("date") String date);

    void deleteVacationRequest(@Param("year") int year, @Param("month") int month, @Param("employeeId") int employeeId);

    void insertShift(Shift shift);

    List<Shift> findMonthlyShift(@Param("year") int year, @Param("month") int month);

    void updateShift(Shift shift);

    void deleteShift(@Param("year") int year, @Param("month") int month);
}
