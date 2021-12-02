package pl.karas.cyclingmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.karas.cyclingmanagementsystem.model.Achievement;
import pl.karas.cyclingmanagementsystem.model.ActivationLink;

import java.util.Optional;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {

}
