package pl.karas.cyclingmanagementsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.karas.cyclingmanagementsystem.model.Journey;
import pl.karas.cyclingmanagementsystem.model.Race;
import pl.karas.cyclingmanagementsystem.model.Address;
import pl.karas.cyclingmanagementsystem.service.AccomodationService;
import pl.karas.cyclingmanagementsystem.service.AddressService;
import pl.karas.cyclingmanagementsystem.service.JourneyService;
import pl.karas.cyclingmanagementsystem.service.RaceService;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin
public class JourneyController {

    @Autowired
    RaceService raceService;

    @Autowired
    JourneyService journeyService;

    @Autowired
    AddressService addressService;

    @Autowired
    AccomodationService accomodationService;

    @PutMapping("/journeys")
    ResponseEntity<Journey> saveJourney(@RequestBody Journey journey, @RequestParam String raceId){

        log.info("saveJourney:: Got journey with address: {} ", journey.getAccomodation().getAddress().getBuildingNumber());
        Optional<Journey> raceJourney = Optional.of(raceService.getRaceById(Long.valueOf(raceId)).get().getJourney());
        if(raceJourney.isPresent()){
            log.info("saveJourney:: raceJourneyIsPresent ", journey.getAccomodation().getAddress().getBuildingNumber());

            this.addressService.save(journey.getAccomodation().getAddress());

            this.accomodationService.save(journey.getAccomodation());

            Journey savedJourney = journeyService.save(journey);
            return ResponseEntity.ok(savedJourney);
        } else {
            log.info("saveJourney:: else ", journey.getAccomodation().getAddress().getBuildingNumber());

            Optional<Race> optRace = raceService.getRaceById(Long.valueOf(raceId));
            if(optRace.isPresent()){
                Race race = optRace.get();
                race.setJourney(journey);
                raceService.save(race);
                Journey savedJourney = raceService.getRaceById(Long.valueOf(raceId)).get().getJourney();
                return ResponseEntity.ok(savedJourney);
            }

        }
        return ResponseEntity.badRequest().build();
    }
}
