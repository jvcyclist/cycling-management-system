package pl.karas.cyclingmanagementsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karas.cyclingmanagementsystem.model.Rider;
import pl.karas.cyclingmanagementsystem.model.Training;
import pl.karas.cyclingmanagementsystem.service.TrainingService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
@CrossOrigin
public class TrainingController {

    @Autowired
    TrainingService trainingService;

    @GetMapping("/trainings")
    List<Training> getAllTrainings(){
        return trainingService.getAllTrainings();
    }


    @GetMapping("/trainings/{id}")
    public ResponseEntity getTrainingById(@PathVariable String id) {
        Optional<Training> trainingByIdOpt = Optional.of(this.trainingService.getTrainingById(Long.valueOf(id)));
        return trainingByIdOpt.isPresent() ?
                ResponseEntity.ok(trainingByIdOpt.get())
                : ResponseEntity.badRequest().body("Training with given id not found");
    }

    @PostMapping("/trainings")
    public ResponseEntity<Training> saveTraining(@RequestBody Training training){
        training.setId(null);
        Training savedRider = this.trainingService.save(training);
        return ResponseEntity.ok(savedRider);
    }

    @PutMapping("/trainings")
    public ResponseEntity<Training> updateTraining(@RequestBody Training training){
        Training savedRider = this.trainingService.save(training);
        return ResponseEntity.ok(savedRider);
    }

    @DeleteMapping("/trainings/{id}")
    public ResponseEntity deleteTraining(@PathVariable String id){
        this.trainingService.delete(Long.valueOf(id));
        return ResponseEntity.ok().build();
    }


}
