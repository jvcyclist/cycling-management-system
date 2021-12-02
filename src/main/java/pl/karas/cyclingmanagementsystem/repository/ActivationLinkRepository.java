package pl.karas.cyclingmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.karas.cyclingmanagementsystem.model.ActivationLink;

import java.util.Optional;

public interface ActivationLinkRepository extends JpaRepository<ActivationLink, Long> {

    Optional<ActivationLink> findByActivationToken(String activationToken);
    Optional<ActivationLink> findByUserId(Long userId);

}
