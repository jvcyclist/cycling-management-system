package pl.karas.cyclingmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karas.cyclingmanagementsystem.model.Race;
import pl.karas.cyclingmanagementsystem.model.Rider;
import pl.karas.cyclingmanagementsystem.service.RaceService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
@CrossOrigin
public class RaceController {

    @Autowired
    RaceService raceService;

    @GetMapping("/races")
    public List<Race> getAllRaces(){
        return this.raceService.getAllRaces();
    }

    @GetMapping("/races/{id}")
    public ResponseEntity getRaceById(@PathVariable String id) {
        Optional<Race> riderByIdOpt = Optional.of(this.raceService.getRaceById(Long.valueOf(id)));
        return riderByIdOpt.isPresent() ?
                ResponseEntity.ok(riderByIdOpt.get())
                : ResponseEntity.badRequest().body("Race with given id not found");
    }
}
