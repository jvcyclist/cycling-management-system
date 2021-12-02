package pl.karas.cyclingmanagementsystem.service.impl;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.karas.cyclingmanagementsystem.CyclingManagementSystemApplication;
import pl.karas.cyclingmanagementsystem.model.Category;
import pl.karas.cyclingmanagementsystem.model.Rider;
import pl.karas.cyclingmanagementsystem.service.RiderService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CyclingManagementSystemApplication.class)
@ActiveProfiles(profiles = "unit")
public class RiderServiceImplTest {

    @Autowired
    RiderService riderService;

    @Disabled
    @Test
    public void twoZakRiders_getRidersWithCategoryInAuthorities_twoZakRiders(){
        //given
        Category zakCategory = Category.builder()
                .id(1L)
                .name("ZAK")
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

        //when




        //then

    }

    @Test
    void getRidersWithSoonExpirationOfMedicalCard(){
        assertTrue(false);
    }



}
