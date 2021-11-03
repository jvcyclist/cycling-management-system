package pl.karas.cyclingmanagementsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.karas.cyclingmanagementsystem.model.Rider;

public interface RiderRepository extends JpaRepository<Rider, Long> {

}
