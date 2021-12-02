package pl.karas.cyclingmanagementsystem.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Data
@ConfigurationProperties(prefix = "cycling-management-system.app.nearestraces")
@Configuration
public class RaceServiceConfig {

    private Duration timeAhead;

}
