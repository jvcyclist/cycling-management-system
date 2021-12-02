package pl.karas.cyclingmanagementsystem.repository;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import pl.karas.cyclingmanagementsystem.CyclingManagementSystemApplication;
import pl.karas.cyclingmanagementsystem.model.Category;
import pl.karas.cyclingmanagementsystem.model.MedicalCard;
import pl.karas.cyclingmanagementsystem.model.Rider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CyclingManagementSystemApplication.class)
@ActiveProfiles(profiles = "unit")
public class RiderRepositoryTest {

    @Autowired
    RiderRepository riderRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MedicalCardRepository medicalCardRepository;

    @AfterEach()
    public void tearDown(){
        this.riderRepository.deleteAll();
        this.categoryRepository.deleteAll();
        this.medicalCardRepository.deleteAll();
    }

    @Test
    public void twoCatZakRidersAndOneCatZakini_findByCategoryNameWithCatZak_expectedTwoRidersZakAndOneRiderZak(){
        //given
        Category zakCategory = Category.builder()
                     .id(1L)
                     .name("ZAK")
                     .gender("MEN")
                     .build();

        Category zakiniCategory = Category.builder()
                    .id(2L)
                    .name("ZAKINI")
                    .gender("MEN")
                    .build();

        Rider firstZakRider = Rider.builder()
                .id(1L)
                .firstName("Adam")
                .lastName("Zakowy")
                .category(zakCategory)
                .build();

        Rider secondZakRider = Rider.builder()
                .id(1L)
                .firstName("Tomek")
                .lastName("Zakowy")
                .category(zakCategory)
                .build();

        Rider firstZakiniRider = Rider.builder()
                .id(2L)
                .firstName("Alicja")
                .lastName("Zakini")
                .category(zakiniCategory)
                .build();

        //when
        categoryRepository.saveAll(List.of(zakCategory, zakiniCategory));
        riderRepository.saveAll(List.of(firstZakRider, secondZakRider, firstZakiniRider));

        List<Rider> zakRiders = riderRepository.findByCategoryName("ZAK");

        //then
        assertThat(zakRiders.size(), is(2));

    }

    @Test
    public void findByMedicalCards_ValidToBetween() {
        //3 zawodnikow
        // 2 z nich powinno sie konczyc ostatnie badanie w przeciagu

        LocalDate expirationDateInRange = LocalDate.of(2021, 5, 1);
        LocalDate expirationDateOutOfRange = LocalDate.of(2021, 9, 1);

        List<Rider> riders = new ArrayList<>();
        List<MedicalCard> medicalCards = new ArrayList<>();
        LongStream.range(1, 5).forEachOrdered(i -> {
            Rider rider = Rider.builder()
                    .id(i)
                    .build();
            riders.add(rider);

            medicalCards.add(
                    MedicalCard.builder()
                            .id(i)
                            .validTo(expirationDateInRange)
                            .rider(rider)
                            .build()
            );
        });

        LongStream.range(5, 7).forEachOrdered(i -> {
            Rider rider = Rider.builder()
                    .id(i)
                    .build();
            riders.add(rider);

            medicalCards.add(
                    MedicalCard.builder()
                            .id(i)
                            .validTo(expirationDateOutOfRange)
                            .rider(rider)
                            .build()
            );
        });

        riderRepository.saveAll(riders);
        medicalCardRepository.saveAll(medicalCards);

        List<Rider> ridersWithSoonExpirationOfMedicalCards = riderRepository.findByMedicalCards_ValidToBetween(LocalDate.of(2021, 04, 1), LocalDate.of(2021, 06, 1));

        assertThat(ridersWithSoonExpirationOfMedicalCards.size(), is(4));


    };







}
