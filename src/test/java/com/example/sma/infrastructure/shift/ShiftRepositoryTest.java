package com.example.sma.infrastructure.shift;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


//qualifiedTableNames = true,caseInsensitiveStrategy = Orthography.LOWERCASE
@DBRider
@SpringBootTest
//@TestPropertySource(locations = "classpath:test.yml")
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
class ShiftRepositoryTest {

    @Autowired
    private ShiftRepository shiftRepository;

//    @BeforeEach
//    @DataSet("vacationList.yml")
//    public void setUpVacationList(){
//
//    }


    @Test
    @DataSet("vacationList.yml")
    public void 休み希望が全件取得できること(){
        assertThat(shiftRepository.findVacationRequest(2022,7,1)).hasSize(2);
    }

    @Test
    public void 休み希望を一件提出できること(){
        shiftRepository.deleteVacationRequest(2022,7,1);
        assertThat(shiftRepository.findVacationRequest(2022,7,1)).hasSize(0);
    }


}
