package pl.karas.cyclingmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.karas.cyclingmanagementsystem.model.Training;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}
