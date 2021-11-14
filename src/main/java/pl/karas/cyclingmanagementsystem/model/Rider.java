package pl.karas.cyclingmanagementsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String licenseNo;
    private String pesel;
    private String gender;
    private String phoneNumber;
    private String mail;
    @ManyToOne
    private Category category;

    @ManyToOne
    private Address address;

    @ManyToMany(cascade = {CascadeType.ALL})
    private Set<Achievement> achievements;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<MedicalCard> medicalCards;
}
