package pl.karas.cyclingmanagementsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.karas.cyclingmanagementsystem.model.Rider;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RiderRepository extends JpaRepository<Rider, Long> {
    List<Rider> findByCategoryName(String categoryName);
    List<Rider> findByCategoryNameIn(Set<String> categoryNames);
    Set<Rider> findByMedicalCards_ValidToBetween(LocalDate startDate, LocalDate endDate);

    @Query(value = "from Rider r join r.journeys j where j.id=:id")
    List<Rider> findByJourneys_Id(Long id);

    Optional<Rider> findByMedicalCards_Id(Long id);
    Optional<Rider> findByAchievements_Id(Long id);

}
