package pl.karas.cyclingmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.karas.cyclingmanagementsystem.model.Journey;

public interface JourneyRepository extends JpaRepository<Journey, Long> {
}
