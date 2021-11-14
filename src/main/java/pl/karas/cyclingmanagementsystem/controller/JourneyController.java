package pl.karas.cyclingmanagementsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karas.cyclingmanagementsystem.model.Journey;
import pl.karas.cyclingmanagementsystem.model.Race;
import pl.karas.cyclingmanagementsystem.service.JourneyService;
import pl.karas.cyclingmanagementsystem.service.RaceService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class JourneyController {

    @Autowired
    RaceService raceService;

    @Autowired
    JourneyService journeyService;

    @PutMapping("/journey")
    ResponseEntity<Journey> saveJourney(@RequestBody Journey journey, @RequestParam String raceId){

        Optional<Journey> raceJourney = Optional.of(raceService.getRaceById(Long.valueOf(raceId)).getJourney());
        if(raceJourney.isPresent()){
            Journey savedJourney = journeyService.save(journey);
            return ResponseEntity.ok(savedJourney);
        } else {
            Race race = raceService.getRaceById(Long.valueOf(raceId));
            race.setJourney(journey);
            raceService.save(race);
            Journey savedJourney = raceService.getRaceById(Long.valueOf(raceId)).getJourney();
            return ResponseEntity.ok(savedJourney);
        }

    }

}
