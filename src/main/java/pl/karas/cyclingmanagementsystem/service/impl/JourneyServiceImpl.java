package pl.karas.cyclingmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karas.cyclingmanagementsystem.model.Journey;
import pl.karas.cyclingmanagementsystem.repository.JourneyRepository;
import pl.karas.cyclingmanagementsystem.service.JourneyService;

@Service
public class JourneyServiceImpl implements JourneyService {

    @Autowired
    JourneyRepository journeyRepository;

    @Override
    public Journey save(Journey journey) {
        return this.journeyRepository.save(journey);
    }
}
