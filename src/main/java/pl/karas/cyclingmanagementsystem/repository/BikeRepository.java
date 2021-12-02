package pl.karas.cyclingmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.karas.cyclingmanagementsystem.model.Bike;

import java.util.List;

public interface BikeRepository extends JpaRepository<Bike, Long> {


}
