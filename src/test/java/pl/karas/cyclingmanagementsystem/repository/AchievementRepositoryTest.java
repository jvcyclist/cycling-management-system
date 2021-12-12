package pl.karas.cyclingmanagementsystem.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.karas.cyclingmanagementsystem.CyclingManagementSystemApplication;
import pl.karas.cyclingmanagementsystem.model.Achievement;
import pl.karas.cyclingmanagementsystem.model.Rider;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CyclingManagementSystemApplication.class)
@ActiveProfiles(profiles = "unit")
public class AchievementRepositoryTest {

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private RiderRepository riderRepository;

    @Test
    public void twoAchievementsInScopeAndTwoOut_getAchievementsByRiderIdAndDateBetween_twoAchievementsExpected () {
        //given
        Rider firstRider = Rider.builder()
                .firstName("Adam")
                .build();

        Rider secondRider = Rider.builder()
                .firstName("Tomasz")
                .build();

        Rider savedFirstRider = this.riderRepository.save(firstRider);
        Rider savedSecondRider = this.riderRepository.save(secondRider);

        Achievement firstAchievementForFirstRider = Achievement.builder()
                .description("Adam")
                .achievementDate(LocalDate.of(2021, 05, 11))
                .rider(savedFirstRider)
                .build();

        Achievement secondAchievementForFirstRider = Achievement.builder()
                .description("Adam")
                .achievementDate(LocalDate.of(2021, 07, 11))
                .rider(savedFirstRider)
                .build();

        Achievement thirdAchievementForFirstRider = Achievement.builder()
                .description("Adam")
                .achievementDate(LocalDate.of(2020, 07, 11))
                .rider(savedFirstRider)
                .build();

        List.of(firstAchievementForFirstRider, secondAchievementForFirstRider, thirdAchievementForFirstRider).forEach(achievement -> {
            Achievement savedAchievement = this.achievementRepository.save(achievement);

        });

        Achievement firstAchievementForSecondRider = Achievement.builder()
                .description("Tomasz")
                .achievementDate(LocalDate.of(2021, 05, 11))
                .rider(savedSecondRider)
                .build();

        Achievement secondAchievementForSecondRider = Achievement.builder()
                .description("Tomasz")
                .achievementDate(LocalDate.of(2020, 07, 11))
                .rider(savedSecondRider)
                .build();

        Achievement thirdAchievementForSecondRider = Achievement.builder()
                .description("Tomasz")
                .achievementDate(LocalDate.of(2020, 07, 11))
                .rider(savedSecondRider)
                .build();

        this.achievementRepository.saveAll(List.of(
                firstAchievementForFirstRider, secondAchievementForFirstRider, thirdAchievementForFirstRider,
                firstAchievementForSecondRider, secondAchievementForSecondRider, thirdAchievementForSecondRider
        ));

        //when
        LocalDate now = LocalDate.of(2021, 11, 11);
        LocalDate startDate = now.with(firstDayOfYear());
        LocalDate endDate = now.with(lastDayOfYear());

        List<Achievement> achievementsByYear = this.achievementRepository.findByAchievementDateBetweenAndRider_Id(
                startDate, endDate, savedFirstRider.getId()
        );

        //then
        assertThat(achievementsByYear.size(), is(2));
        assertThat(achievementsByYear.get(0).getDescription(), is("Adam"));
    }
}