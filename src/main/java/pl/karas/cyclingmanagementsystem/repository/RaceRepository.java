package pl.karas.cyclingmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.karas.cyclingmanagementsystem.model.Race;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RaceRepository extends JpaRepository<Race, Long> {
    List<Race> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
}
