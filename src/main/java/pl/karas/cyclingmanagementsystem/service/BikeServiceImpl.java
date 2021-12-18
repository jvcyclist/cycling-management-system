package pl.karas.cyclingmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karas.cyclingmanagementsystem.model.Bike;
import pl.karas.cyclingmanagementsystem.repository.BikeRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BikeServiceImpl implements BikeService {

    @Autowired
    BikeRepository bikeRepository;

    @Override
    public List<Bike> getAllBikes() {
        return this.bikeRepository.findAll();
    }

    @Override
    public Optional<Bike> getBikeById(Long id) {
        return bikeRepository.findById(id);
    }

    @Override
    public List<Bike> getBikesByCurrentRiderId(Long riderId) {
        return null;
    }

    @Override
    public List<Bike> getAvailableBikes(Set<String> bikeTypes) {
        return null;
    }

    @Override
    public Bike saveBike(Bike bike) {
        return this.bikeRepository.save(bike);
    }

    @Override
    public void deleteBikeById(Long id) {

    }
}
