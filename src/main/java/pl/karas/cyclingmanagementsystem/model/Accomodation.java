package pl.karas.cyclingmanagementsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Accomodation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Address address;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
