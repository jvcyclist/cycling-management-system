package pl.karas.cyclingmanagementsystem.repository;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.karas.cyclingmanagementsystem.CyclingManagementSystemApplication;
import pl.karas.cyclingmanagementsystem.model.MedicalCard;
import pl.karas.cyclingmanagementsystem.model.Rider;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CyclingManagementSystemApplication.class)
@ActiveProfiles(profiles = "unit")
public class MedicalCardRepositoryTest {

    @Autowired
    MedicalCardRepository medicalCardRepository;

    @Autowired
    RiderRepository riderRepository;

    @AfterEach()
    public void tearDown(){
        this.medicalCardRepository.deleteAll();
    }

    @Test
    public void oneMedicalCard_deleteById_NoRecordsExpected(){

        //given
        Rider rider = Rider.builder()
                .id(1L)
                .firstName("Adam")
                .lastName("Zakowy")
                .build();

        Rider savedRide = riderRepository.save(rider);

        MedicalCard medicalCard = MedicalCard.builder()
                .rider(savedRide)
                .build();

        MedicalCard savedMedicalCard = medicalCardRepository.save(medicalCard);

        //when
        medicalCardRepository.deleteById(savedMedicalCard.getId());

        Optional<MedicalCard> optMedicalCard = medicalCardRepository.findById(savedMedicalCard.getId());

        //then
        assertThat(optMedicalCard.isPresent(), is(false));

    }


}