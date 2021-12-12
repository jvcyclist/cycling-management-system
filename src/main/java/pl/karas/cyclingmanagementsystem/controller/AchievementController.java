package pl.karas.cyclingmanagementsystem.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karas.cyclingmanagementsystem.model.Achievement;
import pl.karas.cyclingmanagementsystem.model.Rider;
import pl.karas.cyclingmanagementsystem.service.AchievementService;
import pl.karas.cyclingmanagementsystem.service.RiderService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/api")
@RestController
@CrossOrigin
public class AchievementController {

    @Autowired
    AchievementService achievementService;
    @Autowired
    RiderService riderService;


    @GetMapping("/achievements")
    public ResponseEntity<List<Achievement>> getAchievementsByThisYearForRider(@RequestParam(required = false) String mode, @RequestParam(required = true) String riderId){
        if(mode != null && mode.equals("achievements-this-year")){
            List<Achievement> achievements = this.achievementService.findByAchievementByThisYearAndRiderId(Long.valueOf(riderId));
            return ResponseEntity.ok().body(achievements);
        }
        return ResponseEntity.badRequest().build();
    }


    @PutMapping("/achievements")
    public ResponseEntity<Achievement> updateAchievement(@RequestBody Achievement achievement) {
        log.debug("updateAchievement -  with ID {}", achievement.getId());
        Optional<Rider> riderByAchievement = riderService.getRiderByAchievementId(achievement.getId());
        if(riderByAchievement.isPresent()) {
            log.debug("updateAchievement -  gotRider With Name: {}", riderByAchievement.get().getFirstName());
            achievement.setRider(riderByAchievement.get());
        }
        Achievement savedAchievement = this.achievementService.save(achievement);
        return ResponseEntity.ok(savedAchievement);
    }

    @PostMapping("/achievements")
    public ResponseEntity<Achievement> saveAchievementByRiderId(@RequestBody Achievement achievement, @RequestParam String riderId) {
        Optional<Rider> optRider = riderService.getRiderById(Long.valueOf(riderId));
        if (optRider.isPresent()){
            achievement.setRider(optRider.get());
            achievementService.save(achievement);
        }
        return ResponseEntity.ok(achievement);
    }

    @DeleteMapping("/achievements/{id}")
    public ResponseEntity<String> deleteAchievementById(@PathVariable String id){
        Optional<Achievement> optAchievement = achievementService.getAchievementById(Long.valueOf(id));
        if(optAchievement.isPresent())
        {
            achievementService.deleteAchievementById(Long.valueOf(id));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Achievement with given ID doesn't exist");
    }

}
