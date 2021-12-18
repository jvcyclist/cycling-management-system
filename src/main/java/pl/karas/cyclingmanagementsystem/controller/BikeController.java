package pl.karas.cyclingmanagementsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karas.cyclingmanagementsystem.model.Bike;
import pl.karas.cyclingmanagementsystem.service.BikeService;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin
public class BikeController {

    @Autowired
    BikeService bikeService;

    @PostMapping("/bikes")
    public ResponseEntity<Bike> addBike(@RequestBody Bike bike){
        bike.setId(null);
        Bike savedBike = this.bikeService.saveBike(bike);

        return ResponseEntity.ok(savedBike);
    }

    @GetMapping("/bikes")
    public ResponseEntity<List<Bike>> getAllBikes() {
        List<Bike> bikes = this.bikeService.getAllBikes();
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/bikes/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable Long id) {
        Optional<Bike> optBike = this.bikeService.getBikeById(id);
        if(optBike.isPresent()){
            return ResponseEntity.ok(optBike.get());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/bikes/{id}")
    public ResponseEntity<String> deleteBike(@PathVariable Long id) {
        this.bikeService.deleteBikeById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/bikes")
    public ResponseEntity<Bike> updateBike(@RequestBody Bike bike) {
        Bike savedBike = bikeService.saveBike(bike);
        return ResponseEntity.ok(savedBike);
    }

}
