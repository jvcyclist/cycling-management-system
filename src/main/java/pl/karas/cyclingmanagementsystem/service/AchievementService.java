package pl.karas.cyclingmanagementsystem.service;

import pl.karas.cyclingmanagementsystem.model.Achievement;

import java.util.List;
import java.util.Optional;

public interface AchievementService {
    Achievement save(Achievement achievement);
    List<Achievement> getAll();
    void deleteById(Long id);

}
