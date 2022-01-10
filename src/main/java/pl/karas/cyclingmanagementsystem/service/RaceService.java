package pl.karas.cyclingmanagementsystem.service;

import pl.karas.cyclingmanagementsystem.model.Race;

import java.util.List;
import java.util.Optional;

public interface RaceService {
    List<Race> getAllRaces();
    Optional<Race> getRaceById(Long id);
    Race save(Race race);
    List<Race> getNearestRaces();
    void deleteById(Long id);
}
