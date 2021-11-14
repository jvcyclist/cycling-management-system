package pl.karas.cyclingmanagementsystem.service;

import pl.karas.cyclingmanagementsystem.model.Race;

import java.util.List;

public interface RaceService {
    List<Race> getAllRaces();
    Race getRaceById(Long id);
    Race save(Race race);
}
