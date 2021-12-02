package pl.karas.cyclingmanagementsystem.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate validFrom;
    private LocalDate validTo;
    private String comment;
    private Float weight;
    private Float height;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="rider_id")
    private Rider rider;

}
