package pl.karas.cyclingmanagementsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karas.cyclingmanagementsystem.model.Category;
import pl.karas.cyclingmanagementsystem.model.Rider;
import pl.karas.cyclingmanagementsystem.service.CategoryService;
import pl.karas.cyclingmanagementsystem.service.RiderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin
public class RiderController {

    @Autowired
    private RiderService riderService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/riders")
    public List<Rider> getRiders(@RequestParam(required = false) String mode){
        if(mode != null && mode.equals("medical-card-soon-expired")){
            List<Rider> riders = new ArrayList<>();
            riders.addAll(riderService.getRidersWithSoonExpirationOfMedicalCard());
            return riders;
        }
        return this.riderService.getRidersByCategoryNamesInAuthority();
    }

    @GetMapping("/riders/{id}")
    public ResponseEntity getRiderById(@PathVariable String id) {
        Optional<Rider> riderByIdOpt = this.riderService.getRiderById(Long.valueOf(id));
        return riderByIdOpt.isPresent() ?
                ResponseEntity.ok(riderByIdOpt.get())
                : ResponseEntity.badRequest().body("Rider with given id not found");
    }

    @PostMapping("/riders")
    public ResponseEntity<Rider> saveRider(@RequestBody Rider rider){
        rider.setId(null);
        Rider savedRider = this.riderService.save(rider);
        return ResponseEntity.ok(savedRider);
    }

    @DeleteMapping("/riders/{id}")
    public ResponseEntity deleteRiderById(@PathVariable String id) {
        this.riderService.getRiderById(Long.valueOf(id));
        this.riderService.deleteRider(Long.valueOf(id));
        return
                ResponseEntity.ok().build();
    }

    @PutMapping("/riders")
    public ResponseEntity<Rider> updateRider(@RequestBody Rider rider){
            log.info("RiderController::updateRider: got Rider with ID: {}", rider.getId());
        Optional<Rider> foundRiderOpt = this.riderService.getRiderById(rider.getId());
        if(foundRiderOpt.isPresent()){
            log.info("RiderController::updateRider: Found Rider in repository");
            rider.setId(foundRiderOpt.get().getId());
        }
        if(rider.getCategory() != null && rider.getCategory().getName() != null){
            Optional<Category> foundCategoryOpt = categoryService.getCategoryByName(rider.getCategory().getName());
            if(foundCategoryOpt.isPresent()) {
                rider.setCategory(foundCategoryOpt.get());
            }
            else{
                log.info("RiderController::updateRider: Not found category with name: {}", rider.getCategory().getName());
            }
        }
        Rider savedRider = rider;
                this.riderService.save(rider);
        return ResponseEntity.ok(savedRider);
    }
}
