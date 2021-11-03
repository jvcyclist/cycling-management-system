package pl.karas.cyclingmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.karas.cyclingmanagementsystem.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
