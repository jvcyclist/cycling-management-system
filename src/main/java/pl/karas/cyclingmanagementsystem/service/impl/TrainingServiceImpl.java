package pl.karas.cyclingmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.karas.cyclingmanagementsystem.model.Training;
import pl.karas.cyclingmanagementsystem.repository.TrainingRepository;
import pl.karas.cyclingmanagementsystem.service.TrainingService;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    TrainingRepository trainingRepository;


    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public Training getTrainingById(Long id) {
        return trainingRepository.findById(id).get();
    }

    @Override
    public Training save(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public void delete(Long id) {
        this.trainingRepository.deleteById(id);
    }
}
