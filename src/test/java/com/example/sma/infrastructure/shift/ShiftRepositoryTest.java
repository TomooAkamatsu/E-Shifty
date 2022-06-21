package com.example.sma.infrastructure.shift;

import com.example.sma.domain.models.shift.Shift;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@TestPropertySource(locations = "classpath:test.yml")
class ShiftRepositoryTest {

    @Autowired
    ShiftRepository shiftRepository;

    @Test
    void 確認(){
        List<Shift> shiftList = shiftRepository.findMonthlyShift(2022,7);
        assertThat(shiftList).isEqualTo(Collections.EMPTY_LIST);
    }


}
