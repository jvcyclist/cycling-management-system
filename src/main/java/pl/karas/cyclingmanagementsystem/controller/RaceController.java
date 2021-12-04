package pl.karas.cyclingmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karas.cyclingmanagementsystem.model.Accomodation;
import pl.karas.cyclingmanagementsystem.model.Journey;
import pl.karas.cyclingmanagementsystem.model.Race;
import pl.karas.cyclingmanagementsystem.model.Rider;
import pl.karas.cyclingmanagementsystem.service.AccomodationService;
import pl.karas.cyclingmanagementsystem.service.JourneyService;
import pl.karas.cyclingmanagementsystem.service.RaceService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
@CrossOrigin
public class RaceController {

    @Autowired
    RaceService raceService;

    @Autowired
    JourneyService journeyService;

    @Autowired
    AccomodationService accomodationService;

    @GetMapping("/races")
    public List<Race> getAllRaces(@RequestParam(required = false) String mode){
        if(mode != null && mode.equals("nearest-races")){
            return this.raceService.getNearestRaces();
        }
        return this.raceService.getAllRaces();
    }

    @GetMapping("/races/{id}")
    public ResponseEntity getRaceById(@PathVariable String id) {
        Optional<Race> riderByIdOpt = Optional.of(this.raceService.getRaceById(Long.valueOf(id)));
        return riderByIdOpt.isPresent() ?
                ResponseEntity.ok(riderByIdOpt.get())
                : ResponseEntity.badRequest().body("Race with given id not found");
    }

    @PostMapping("/races")
    public ResponseEntity<Race> saveRace(@RequestBody Race race){
        Accomodation accomodation = new Accomodation();
        Accomodation savedAccomodation = accomodationService.save(accomodation);

        Journey journey = Journey.builder()
                .accomodation(savedAccomodation)
                .build();

        Journey savedJourney = journeyService.save(journey);

        race.setId(null);
        race.setJourney(savedJourney);
        Race savedRace = raceService.save(race);
        return ResponseEntity.ok(savedRace);
    }

    @PutMapping("/races")
    public ResponseEntity<Race> updateRace(@RequestBody Race race){
        Race updatedRace = this.raceService.save(race);
        return ResponseEntity.ok(updatedRace);
    }





}
