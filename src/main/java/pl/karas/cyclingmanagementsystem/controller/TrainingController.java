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
@CrossOrigin(origins = "http://localhost:4200")
public class TrainingController {

    @Autowired
    TrainingService trainingService;

    @GetMapping("/training")
    List<Training> getAllTrainings(){
        return trainingService.getAllTrainings();
    }


    @GetMapping("/training/{id}")
    public ResponseEntity getTrainingById(@PathVariable String id) {
        Optional<Training> trainingByIdOpt = Optional.of(this.trainingService.getTrainingById(Long.valueOf(id)));
        return trainingByIdOpt.isPresent() ?
                ResponseEntity.ok(trainingByIdOpt.get())
                : ResponseEntity.badRequest().body("Training with given id not found");
    }

}
