package pl.karas.cyclingmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.karas.cyclingmanagementsystem.model.Accomodation;

public interface AccomodationRepository extends JpaRepository<Accomodation, Long> {
}
