package pl.karas.cyclingmanagementsystem.service;

import pl.karas.cyclingmanagementsystem.model.ActivationLink;

import java.util.Optional;

public interface ActivationLinkService {

    Optional<ActivationLink> getActivationLinkByActivationToken(String activationToken);
    Optional<ActivationLink> getActivationLinkByUserId(Long userId);
    ActivationLink save(ActivationLink activationLink);
    void deleteActivationLinkById(Long id);
}
