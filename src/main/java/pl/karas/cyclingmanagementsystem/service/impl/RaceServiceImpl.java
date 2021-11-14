package pl.karas.cyclingmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karas.cyclingmanagementsystem.model.Race;
import pl.karas.cyclingmanagementsystem.repository.RaceRepository;
import pl.karas.cyclingmanagementsystem.service.RaceService;

import java.util.List;

@Service
public class RaceServiceImpl implements RaceService {
    @Autowired
    RaceRepository raceRepository;

    @Override
    public List<Race> getAllRaces() {
        return this.raceRepository.findAll();
    }

    @Override
    public Race getRaceById(Long id) {
        return this.raceRepository.findById(id).get();
    }

    @Override
    public Race save(Race race) {
        return this.raceRepository.save(race);
    }


}
