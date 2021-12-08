package pl.karas.cyclingmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karas.cyclingmanagementsystem.model.MedicalCard;
import pl.karas.cyclingmanagementsystem.model.Rider;
import pl.karas.cyclingmanagementsystem.service.MedicalCardService;
import pl.karas.cyclingmanagementsystem.service.RiderService;

import java.util.Optional;

@RequestMapping("/api")
@RestController
@CrossOrigin
public class MedicalCardController {

    @Autowired
    MedicalCardService medicalCardService;
    @Autowired
    RiderService riderService;

    @PutMapping("/medical-cards")
    public ResponseEntity<MedicalCard> updateMedicalCard(@RequestBody MedicalCard medicalCard){
        Optional<Rider> riderByMedicalCardId = riderService.getRiderByMedicalCardId(medicalCard.getId());
        if(riderByMedicalCardId.isPresent()) {
            medicalCard.setRider(riderByMedicalCardId.get());
        }
        MedicalCard savedMedicalCard = this.medicalCardService.save(medicalCard);
        return ResponseEntity.ok(savedMedicalCard);
    }

    @PostMapping("/medical-cards")
    public ResponseEntity<MedicalCard> saveMedicalCardByRiderId(@RequestBody MedicalCard medicalCard, @RequestParam String riderId) {
        Optional<Rider> optRider = this.riderService.getRiderById(Long.valueOf(riderId));
        if(optRider.isPresent()){
            Rider rider = optRider.get();
            medicalCard.setRider(rider);
        }
        medicalCardService.save(medicalCard);

        return ResponseEntity.ok(medicalCard);
    }

    @DeleteMapping("/medical-cards/{id}")
    public ResponseEntity<String> deleteMedicalCardById (@PathVariable String id) {
        Optional<MedicalCard> optionalMedicalCard = medicalCardService.getMedicalCardById(Long.valueOf(id));
        if(optionalMedicalCard.isPresent()) {
            medicalCardService.deleteMedicalCard(optionalMedicalCard.get());
        } else {
            return ResponseEntity.badRequest().body("Medical Card with given ID doesn't exist");
        }
        return ResponseEntity.ok().build();
    }
}