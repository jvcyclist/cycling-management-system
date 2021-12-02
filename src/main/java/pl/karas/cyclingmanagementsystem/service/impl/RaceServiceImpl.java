package pl.karas.cyclingmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import pl.karas.cyclingmanagementsystem.model.Race;
import pl.karas.cyclingmanagementsystem.repository.RaceRepository;
import pl.karas.cyclingmanagementsystem.service.RaceService;
import pl.karas.cyclingmanagementsystem.service.config.RaceServiceConfig;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class RaceServiceImpl implements RaceService {

    @Autowired
    private Clock clock;

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private RaceServiceConfig raceServiceConfig;

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

    @Override
    public List<Race> getNearestRaces() {
        Duration durationAhead = raceServiceConfig.getTimeAhead();
        LocalDate maxDate = LocalDate.now(this.clock).plusDays(durationAhead.toDays());
        LocalDate todayDate = LocalDate.now(this.clock);
        List<Race> test = raceRepository.findByStartDateBetween(todayDate, maxDate);
        return raceRepository.findByStartDateBetween(todayDate, maxDate);
    }

}
