package pl.karas.cyclingmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karas.cyclingmanagementsystem.model.*;
import pl.karas.cyclingmanagementsystem.repository.AddressRepository;
import pl.karas.cyclingmanagementsystem.service.*;

import java.util.*;

@RequestMapping("/api")
@RestController
@CrossOrigin
public class RaceController {

    @Autowired
    RiderService riderService;

    @Autowired
    RaceService raceService;

    @Autowired
    JourneyService journeyService;

    @Autowired
    AccomodationService accomodationService;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/races")
    public List<Race> getAllRaces(@RequestParam(required = false) String mode){
        if(mode != null && mode.equals("nearest-races")){
            return this.raceService.getNearestRaces();
        }
        return this.raceService.getAllRaces();
    }

    @GetMapping("/races/{id}")
    public ResponseEntity getRaceById(@PathVariable String id) {
        Optional<Race> riderByIdOpt = this.raceService.getRaceById(Long.valueOf(id));
        return riderByIdOpt.isPresent() ?
                ResponseEntity.ok(riderByIdOpt.get())
                : ResponseEntity.badRequest().body("Race with given id not found");
    }

    @PostMapping("/races")
    public ResponseEntity<Race> saveRace(@RequestBody Race race){
        if(race.getCategories() != null){
            Set<Category> categoriesToSave = new HashSet<>();
            race.getCategories().forEach(cat -> {
                Optional<Category> category = this.categoryService.getCategoryByName(cat.getName());
                if(category.isPresent()){
                    categoriesToSave.add(category.get());
                }
            });
            race.setCategories(categoriesToSave);
        }

        Address address = new Address();
        Address savedAddress = this.addressRepository.save(address);


        Accomodation accomodation = new Accomodation();
        accomodation.setAddress(savedAddress);
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
        if(race.getCategories() != null){
            Set<Category> categoriesToSave = new HashSet<>();
            race.getCategories().forEach(cat -> {
            Optional<Category> category = this.categoryService.getCategoryByName(cat.getName());
            if(category.isPresent()){
                categoriesToSave.add(category.get());
            }
            });
            race.setCategories(categoriesToSave);
        }
        Race updatedRace = this.raceService.save(race);
        return ResponseEntity.ok(updatedRace);
    }

    @DeleteMapping("/races/{id}")
    public ResponseEntity<String> deleteRaceById(@PathVariable Long id){
        Optional<Race> optRace = raceService.getRaceById(Long.valueOf(id));
        if(optRace.isPresent()) {
            this.raceService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/races/{id}/riders")
    public ResponseEntity<Race> updateRidersOfRace(@PathVariable Long id, @RequestBody List<Rider> riders){
        Optional<Race> optRace = raceService.getRaceById(id);
        if(optRace.isPresent()) {
            Race raceToUpdate = optRace.get();

        List<Rider> ridersToSave = new ArrayList<>();
            riders.forEach(rider -> {
                Optional<Rider> optRider = this.riderService.getRiderById(rider.getId());
                if(optRider.isPresent()){
                    ridersToSave.add(rider);
                }
            });
            raceToUpdate.getJourney().setRiders(ridersToSave);
            this.raceService.save(raceToUpdate);
        }
        return ResponseEntity.ok(null);
    }



}
