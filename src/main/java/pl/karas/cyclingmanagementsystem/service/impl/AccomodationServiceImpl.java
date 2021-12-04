package pl.karas.cyclingmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karas.cyclingmanagementsystem.model.Accomodation;
import pl.karas.cyclingmanagementsystem.repository.AccomodationRepository;
import pl.karas.cyclingmanagementsystem.service.AccomodationService;

@Service
public class AccomodationServiceImpl implements AccomodationService {

    @Autowired
    private AccomodationRepository accomodationRepository;

    @Override
    public Accomodation save(Accomodation accomodation) {
        return this.accomodationRepository.save(accomodation);
    }
}
