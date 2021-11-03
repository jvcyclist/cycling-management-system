package pl.karas.cyclingmanagementsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.karas.cyclingmanagementsystem.model.Equipment;
import pl.karas.cyclingmanagementsystem.service.EquipmentService;

import java.util.List;

@RestController
@CrossOrigin
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @GetMapping("api/equipments")
    public List<Equipment> getAllEquipment(){
        return this.equipmentService.getAllEquipment();
    }

}
