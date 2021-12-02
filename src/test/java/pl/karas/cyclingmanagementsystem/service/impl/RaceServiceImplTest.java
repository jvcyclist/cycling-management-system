package pl.karas.cyclingmanagementsystem.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.karas.cyclingmanagementsystem.CyclingManagementSystemApplication;
import pl.karas.cyclingmanagementsystem.model.Race;
import pl.karas.cyclingmanagementsystem.repository.RaceRepository;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ActiveProfiles(profiles = "unit")
@SpringBootTest(classes = CyclingManagementSystemApplication.class)
@RunWith(SpringRunner.class)
class RaceServiceImplTest {
    @Autowired
    private RaceServiceImpl raceService;

    @MockBean
    private Clock clock;

    @MockBean
    private RaceRepository raceRepository;

    private final static LocalDate LOCAL_DATE = LocalDate.of(2021, 04, 11);
    private Clock fixedClock;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
        fixedClock = Clock.fixed(LOCAL_DATE.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        doReturn(fixedClock.instant()).when(clock).instant();
        doReturn(fixedClock.getZone()).when(clock).getZone();
        doReturn(Collections.singletonList(new Race())).when(raceRepository).findByStartDateBetween(any(), any());
    }

    @Test
    void getNearestRaces() {

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

        //when
        Set.of(raceInDateRange, firstRaceOutOfDateRange, secondRaceOutOfDateRange).stream()
                .peek(race -> raceService.save(race));
        List<Race> racesInDateRange = raceService.getNearestRaces();

        //then
        assertThat(racesInDateRange.size(), is(1));
    }

}