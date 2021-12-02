package pl.karas.cyclingmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karas.cyclingmanagementsystem.model.ActivationLink;
import pl.karas.cyclingmanagementsystem.repository.ActivationLinkRepository;
import pl.karas.cyclingmanagementsystem.service.ActivationLinkService;

import java.util.Optional;

@Service
public class ActivationLinkServiceImpl implements ActivationLinkService {

    @Autowired
    ActivationLinkRepository activationLinkRepository;

    @Override
    public Optional<ActivationLink> getActivationLinkByActivationToken(String activationToken) {
        return activationLinkRepository.findByActivationToken(activationToken);
    }

    @Override
    public Optional<ActivationLink> getActivationLinkByUserId(Long userId) {
        return activationLinkRepository.findByUserId(userId);
    }

    @Override
    public ActivationLink save(ActivationLink activationLink) {
        return activationLinkRepository.save(activationLink);
    }

    @Override
    public void deleteActivationLinkById(Long id) {
        this.activationLinkRepository.deleteById(id);
    }

}
