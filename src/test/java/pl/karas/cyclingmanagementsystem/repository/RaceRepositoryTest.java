package pl.karas.cyclingmanagementsystem.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.karas.cyclingmanagementsystem.CyclingManagementSystemApplication;
import pl.karas.cyclingmanagementsystem.model.Race;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CyclingManagementSystemApplication.class)
@ActiveProfiles(profiles = "unit")
public class RaceRepositoryTest {

    @Autowired
    private RaceRepository raceRepository;

    @Test
    public void threeRacesOneInDateRange_findByStartDateBetween_shouldReturnOneRaceInDateRange(){
        //given
        LocalDate startRangeDate = LocalDate.of(2021, 4, 11);
        LocalDate endRangeDate = LocalDate.of(2021, 5, 11);

        Race raceInDateRange =  Race.builder()
                .id(1L)
                .startDate(LocalDate.of(2021, 4, 12))
                .build();

        Race firstRaceOutOfDateRange =  Race.builder()
                .id(2L)
                .startDate(LocalDate.of(2021, 7, 12))
                .build();

        Race secondRaceOutOfDateRange =  Race.builder()
                .id(3L)
                .startDate(LocalDate.of(2021, 5, 12))
                .build();

        raceRepository.saveAll(Set.of(
                raceInDateRange, firstRaceOutOfDateRange, secondRaceOutOfDateRange
        ));

        //when
        List<Race> racesInRangeBetween = raceRepository.findByStartDateBetween(startRangeDate, endRangeDate);

        //then
        assertThat(racesInRangeBetween.size(),  is(1));
        assertThat(racesInRangeBetween.get(0).getId(), is(1L));

    }

}