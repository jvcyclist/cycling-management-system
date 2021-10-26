package pl.karas.cyclingmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karas.cyclingmanagementsystem.model.Rider;
import pl.karas.cyclingmanagementsystem.repository.RiderRepository;
import pl.karas.cyclingmanagementsystem.service.RiderService;

import java.util.List;

@Service
public class RiderServiceImpl implements RiderService {

    @Autowired
    private RiderRepository riderRepository;

    @Override
    public List<Rider> getAllRiders() {
        return this.riderRepository.findAll();
    }
}
