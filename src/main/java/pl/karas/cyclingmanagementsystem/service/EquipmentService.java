package pl.karas.cyclingmanagementsystem.service;

import pl.karas.cyclingmanagementsystem.model.Equipment;

import java.util.List;

public interface EquipmentService {
    List<Equipment> getAllEquipment();
    Equipment saveEquipment(Equipment equipment);
    Equipment getEquipmentById(Long id);
}
