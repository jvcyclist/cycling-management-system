package pl.karas.cyclingmanagementsystem.service;

import pl.karas.cyclingmanagementsystem.model.Rider;

import java.util.List;


public interface RiderService {

    List<Rider> getAllRiders();

    Rider getRiderById(Long id);

    Rider save(Rider rider);

    void deleteRider(Long id);
}
