package pl.karas.cyclingmanagementsystem.service;

import pl.karas.cyclingmanagementsystem.model.Rider;

import java.util.List;
import java.util.Set;


public interface RiderService {

    List<Rider> getAllRiders();

    Rider getRiderById(Long id);

    Rider save(Rider rider);

    void deleteRider(Long id);

    List<Rider> getRidersByCategoryNamesInAuthority();

    List<Rider> getRidersByCategoryNames(Set<String> categories);

    List<Rider> getRidersWithSoonExpirationOfMedicalCard();
}
