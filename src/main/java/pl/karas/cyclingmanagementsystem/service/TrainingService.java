package pl.karas.cyclingmanagementsystem.service;

import pl.karas.cyclingmanagementsystem.model.Rider;
import pl.karas.cyclingmanagementsystem.model.Training;

import java.util.List;

public interface TrainingService {
    List<Training> getAllTrainings();

    Training getTrainingById(Long id);
}
