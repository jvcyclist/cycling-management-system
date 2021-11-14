package pl.karas.cyclingmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.karas.cyclingmanagementsystem.model.Achievement;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
