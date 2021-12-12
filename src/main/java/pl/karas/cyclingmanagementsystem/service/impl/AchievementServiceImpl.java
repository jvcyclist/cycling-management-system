package pl.karas.cyclingmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karas.cyclingmanagementsystem.model.Achievement;
import pl.karas.cyclingmanagementsystem.repository.AchievementRepository;
import pl.karas.cyclingmanagementsystem.service.AchievementService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
public class AchievementServiceImpl implements AchievementService {

    @Autowired
    AchievementRepository achievementRepository;

    @Override
    public Achievement save(Achievement achievement) {
        return this.achievementRepository.save(achievement);
    }

    @Override
    public List<Achievement> getAll() {
        return this.achievementRepository.findAll();
    }

    @Override
    public void deleteAchievementById(Long id) {
        this.achievementRepository.deleteById(id);
    }

    @Override
    public void deleteAchievement(Achievement achievement) {
        this.achievementRepository.delete(achievement);
    }

    @Override
    public Optional<Achievement> getAchievementById(Long id) {
        return this.achievementRepository.findById(id);
    }

    @Override
    public List<Achievement> findByAchievementByThisYearAndRiderId(Long riderId) {
        LocalDate startDate = LocalDate.now().with(firstDayOfYear()).minusDays(1);
        LocalDate endDate = LocalDate.now().with(lastDayOfYear()).plusDays(1);
        return this.achievementRepository.findByAchievementDateBetweenAndRider_Id(startDate, endDate, riderId);
    }
}
