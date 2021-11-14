package pl.karas.cyclingmanagementsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karas.cyclingmanagementsystem.model.Equipment;
import pl.karas.cyclingmanagementsystem.service.EquipmentService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
@CrossOrigin
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @GetMapping("/equipment")
    public List<Equipment> getAllEquipment(){
        return this.equipmentService.getAllEquipment();
    }

    @PutMapping("/equipment")
    public ResponseEntity<Equipment> updateEquipment(@RequestBody Equipment equipment){
        Equipment savedEquipment = this.equipmentService.saveEquipment(equipment);
        return ResponseEntity.ok(savedEquipment);
    }

    @PostMapping("/equipment")
    public ResponseEntity<Equipment> saveEquipment(@RequestBody Equipment equipment){
        Equipment savedEquipment = this.equipmentService.saveEquipment(equipment);
        return ResponseEntity.ok(savedEquipment);
    }

   /* @GetMapping("/equipment/{id}")
    public ResponseEntity<> getEquipmentById(@PathVariable String id){
        Optional<Equipment> optionalEquipment = Optional.ofNullable(this.equipmentService.getEquipmentById(Long.valueOf(id)));

        return optionalEquipment.isPresent() ?
                ResponseEntity.ok(optionalEquipment.get())
                : ResponseEntity.badRequest().body("Race with given id not found");
    }*/


}
