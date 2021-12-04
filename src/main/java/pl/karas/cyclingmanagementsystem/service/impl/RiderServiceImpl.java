package pl.karas.cyclingmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.karas.cyclingmanagementsystem.model.Rider;
import pl.karas.cyclingmanagementsystem.repository.RiderRepository;
import pl.karas.cyclingmanagementsystem.service.RiderService;
import pl.karas.cyclingmanagementsystem.service.config.RaceServiceConfig;
import pl.karas.cyclingmanagementsystem.service.config.RiderServiceConfig;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RiderServiceImpl implements RiderService {

    @Autowired
    private Clock clock;

    @Autowired
    private RiderServiceConfig riderServiceConfig;

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

    @Override
    public List<Rider> getRidersByCategoryNamesInAuthority() {
        Set<String> categoryNamesFromAuthority  =
                    SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                        .map(authority -> authority.getAuthority())
                        .filter(authorityName -> authorityName.startsWith("ROLE_COACH"))
                        .map(authorityName -> authorityName.replace("ROLE_COACH_", ""))
                        .collect(Collectors.toSet());

        return this.riderRepository.findByCategoryNameIn(categoryNamesFromAuthority);
    }

    @Override
    public List<Rider> getRidersByCategoryNames(Set<String> categories) {
        return this.riderRepository.findByCategoryNameIn(categories);
    }

    @Override
    public Set<Rider> getRidersWithSoonExpirationOfMedicalCard() {
        return this.riderRepository.findByMedicalCards_ValidToBetween(LocalDate.now(clock), LocalDate.now(this.clock).plusDays(riderServiceConfig.getTimeAhead().toDays()));
    }

    @Override
    public Optional<Rider> getRiderByMedicalCardId(Long id) {
        return this.riderRepository.findByMedicalCards_Id(id);
    }

    @Override
    public Optional<Rider> getRiderByAchievementId(Long id) {
        return this.riderRepository.findByMedicalCards_Id(id);
    }


}