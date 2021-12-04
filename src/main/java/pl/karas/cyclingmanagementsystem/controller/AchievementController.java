package pl.karas.cyclingmanagementsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karas.cyclingmanagementsystem.model.Achievement;
import pl.karas.cyclingmanagementsystem.model.Rider;
import pl.karas.cyclingmanagementsystem.service.AchievementService;
import pl.karas.cyclingmanagementsystem.service.RiderService;

import java.util.Optional;

@RequestMapping("/api")
@RestController
@CrossOrigin
public class AchievementController {

    @Autowired
    AchievementService achievementService;
    @Autowired
    RiderService riderService;

    @PutMapping("/achievement")
    public ResponseEntity<Achievement> updateAchievement(@RequestBody Achievement achievement) {
        Optional<Rider> riderByAchievement = riderService.getRiderByAchievementId(achievement.getId());
        if(riderByAchievement.isPresent()) {
            achievement.setRider(riderByAchievement.get());
        }
        Achievement savedAchievement = this.achievementService.save(achievement);
        return ResponseEntity.ok(savedAchievement);
    }

    @PostMapping("/achievement")
    public ResponseEntity<Achievement> saveAchievementByRiderId(@RequestBody Achievement achievement, @RequestParam String riderId) {
        Rider rider = this.riderService.getRiderById(Long.valueOf(riderId));

        achievement.setRider(rider);
        achievementService.save(achievement);

        return ResponseEntity.ok(achievement);
    }

}
