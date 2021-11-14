package pl.karas.cyclingmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karas.cyclingmanagementsystem.model.Equipment;
import pl.karas.cyclingmanagementsystem.repository.EquipmentRepository;
import pl.karas.cyclingmanagementsystem.service.EquipmentService;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    EquipmentRepository equipmentRepository;

    @Override
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment saveEquipment(Equipment equipment) {
        return this.equipmentRepository.save(equipment);
    }

    @Override
    public Equipment getEquipmentById(Long id) {
        return this.getEquipmentById(id);
    }
}
