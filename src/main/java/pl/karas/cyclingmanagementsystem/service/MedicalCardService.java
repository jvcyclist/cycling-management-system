package pl.karas.cyclingmanagementsystem.service;

import pl.karas.cyclingmanagementsystem.model.MedicalCard;

import java.util.Optional;

public interface MedicalCardService {

    MedicalCard save(MedicalCard medicalCard);
    Optional<MedicalCard> getMedicalCardById(Long id);
}
