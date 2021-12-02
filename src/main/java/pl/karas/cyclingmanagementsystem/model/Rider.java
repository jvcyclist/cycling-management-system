package pl.karas.cyclingmanagementsystem.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @JsonManagedReference
    @OneToMany(mappedBy = "rider", targetEntity = MedicalCard.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MedicalCard> medicalCards;
}
