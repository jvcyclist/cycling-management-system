package pl.karas.cyclingmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karas.cyclingmanagementsystem.model.MedicalCard;
import pl.karas.cyclingmanagementsystem.repository.MedicalCardRepository;
import pl.karas.cyclingmanagementsystem.service.MedicalCardService;

@Service
public class MedicalCardServiceImpl implements MedicalCardService {

    @Autowired
    MedicalCardRepository medicalCardRepository;

    @Override
    public MedicalCard save(MedicalCard medicalCard) {
        return this.medicalCardRepository.save(medicalCard);
    }
}
