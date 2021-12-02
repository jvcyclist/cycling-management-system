package pl.karas.cyclingmanagementsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.karas.cyclingmanagementsystem.model.Rider;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RiderRepository extends JpaRepository<Rider, Long> {
    List<Rider> findByCategoryName(String categoryName);
    List<Rider> findByCategoryNameIn(Set<String> categoryNames);
    List<Rider> findByMedicalCards_ValidToBetween(LocalDate startDate, LocalDate endDate);

}
