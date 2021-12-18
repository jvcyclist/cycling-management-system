package pl.karas.cyclingmanagementsystem.service;

import pl.karas.cyclingmanagementsystem.model.Bike;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BikeService {
    List<Bike> getAllBikes();
    Optional<Bike> getBikeById(Long id);
    List<Bike> getBikesByCurrentRiderId(Long riderId);
    List<Bike> getAvailableBikes(Set<String> bikeTypes);
    Bike saveBike(Bike bike);
    void deleteBikeById(Long id);
}
