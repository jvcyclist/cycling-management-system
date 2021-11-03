package pl.karas.cyclingmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import pl.karas.cyclingmanagementsystem.model.Rider;
import pl.karas.cyclingmanagementsystem.service.RiderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class RiderController {

    @Autowired
    private RiderService riderService;

    @GetMapping("/rider")
    public List<Rider> getRiders(){
        return this.riderService.getAllRiders();
    }

    @GetMapping("/rider/{id}")
    public ResponseEntity getRiderById(@PathVariable String id) {
        Optional<Rider> riderByIdOpt = Optional.of(this.riderService.getRiderById(Long.valueOf(id)));
        return riderByIdOpt.isPresent() ?
                ResponseEntity.ok(riderByIdOpt.get())
                : ResponseEntity.badRequest().body("Rider with given id not found");
    }

    @PostMapping("/rider")
    public ResponseEntity<Rider> saveRider(@RequestBody Rider rider){
        rider.setId(null);
        Rider savedRider = this.riderService.save(rider);
        return ResponseEntity.ok(savedRider);
    }

    @DeleteMapping("/rider/{id}")
    public ResponseEntity deleteRiderById(@PathVariable String id) {
        this.riderService.deleteRider(Long.valueOf(id));
        return
                ResponseEntity.ok().build();

    }
}
