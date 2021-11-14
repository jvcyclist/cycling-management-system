package pl.karas.cyclingmanagementsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karas.cyclingmanagementsystem.model.Achievement;
import pl.karas.cyclingmanagementsystem.model.Rider;
import pl.karas.cyclingmanagementsystem.service.AchievementService;
import pl.karas.cyclingmanagementsystem.service.RiderService;

import java.util.Comparator;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AchievementController {

    @Autowired
    AchievementService achievementService;
    @Autowired
    RiderService riderService;

    @PutMapping("/achievement")
    public ResponseEntity<Achievement> updateAchievement(@RequestBody Achievement achievement) {
        Achievement savedAchievement = this.achievementService.save(achievement);
        return ResponseEntity.ok(savedAchievement);
    }

    @PostMapping("/achievement")
    public ResponseEntity<Achievement> saveAchievementByRiderId(@RequestBody Achievement achievement, @RequestParam String riderId) {
        Rider rider = this.riderService.getRiderById(Long.valueOf(riderId));
        rider.getAchievements().add(achievement);
        this.riderService.save(rider);
        return ResponseEntity.ok(achievement);
    }

}
