package pl.karas.cyclingmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.karas.cyclingmanagementsystem.model.MedicalCard;
import pl.karas.cyclingmanagementsystem.model.Rider;
import pl.karas.cyclingmanagementsystem.service.MedicalCardService;
import pl.karas.cyclingmanagementsystem.service.RiderService;

@RequestMapping("/api")
@RestController
@CrossOrigin
public class MedicalCardController {

    @Autowired
    MedicalCardService medicalCardService;

    @Autowired
    RiderService riderService;

    @PutMapping("/medical-card")
    public ResponseEntity<MedicalCard> updateMedicalCard(@RequestBody MedicalCard medicalCard){
        MedicalCard savedMedicalCard = this.medicalCardService.save(medicalCard);
        return ResponseEntity.ok(savedMedicalCard);
    }

    @PostMapping("/medical-card")
    public ResponseEntity<MedicalCard> saveMedicalCardByRiderId(@RequestBody MedicalCard medicalCard, @RequestParam String riderId) {
        Rider rider = this.riderService.getRiderById(Long.valueOf(riderId));
        rider.getMedicalCards().add(medicalCard);
        this.riderService.save(rider);
        return ResponseEntity.ok(medicalCard);
    }

}

