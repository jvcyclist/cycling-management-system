package pl.karas.cyclingmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.karas.cyclingmanagementsystem.model.Race;

public interface RaceRepository extends JpaRepository<Race, Long> {
}
