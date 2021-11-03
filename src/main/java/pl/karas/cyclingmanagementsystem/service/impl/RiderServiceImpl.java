package pl.karas.cyclingmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karas.cyclingmanagementsystem.model.Rider;
import pl.karas.cyclingmanagementsystem.repository.RiderRepository;
import pl.karas.cyclingmanagementsystem.service.RiderService;

import java.util.List;
import java.util.Optional;

@Service
public class RiderServiceImpl implements RiderService {

    @Autowired
    private RiderRepository riderRepository;

    @Override
    public List<Rider> getAllRiders() {
        return this.riderRepository.findAll();
    }

    @Override
    public Rider getRiderById(Long id) {
        return this.riderRepository.findById(id).get();
    }

    @Override
    public Rider save(Rider rider) {
        return this.riderRepository.save(rider);
    }

    @Override
    public void deleteRider(Long id) {
        this.riderRepository.deleteById(id);
    }
}
