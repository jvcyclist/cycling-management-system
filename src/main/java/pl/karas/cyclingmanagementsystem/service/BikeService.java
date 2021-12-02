package pl.karas.cyclingmanagementsystem.service;

import pl.karas.cyclingmanagementsystem.model.Bike;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BikeService {
    Optional<Bike> getBikeById(Long id);
    List<Bike> getBikesByCurrentUserId(Long userId);
    List<Bike> getFreeBikes(Set<String> bikeTypes);
}
