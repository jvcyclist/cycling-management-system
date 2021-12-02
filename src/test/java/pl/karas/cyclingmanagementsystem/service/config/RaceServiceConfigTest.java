package pl.karas.cyclingmanagementsystem.service.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RaceServiceConfig.class)
@ActiveProfiles(profiles = "unit")
@EnableConfigurationProperties
public class RaceServiceConfigTest {

    @Autowired
    RaceServiceConfig raceServiceConfig;

    @Test
    public void durationIsNotNull(){
        assertTrue(raceServiceConfig.getTimeAhead() != null);
    }

}