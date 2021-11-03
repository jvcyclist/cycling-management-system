package pl.karas.cyclingmanagementsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Journey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Accomodation accomodation;
    @ManyToMany
    private Set<Rider> riders;
}
