package pl.karas.cyclingmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karas.cyclingmanagementsystem.model.MedicalCard;

@Repository
public interface MedicalCardRepository extends JpaRepository<MedicalCard, Long> {
}
