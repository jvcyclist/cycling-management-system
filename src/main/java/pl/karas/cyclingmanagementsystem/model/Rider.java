package pl.karas.cyclingmanagementsystem.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "rider", targetEntity = Achievement.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Achievement> achievements;

    @JsonManagedReference
    @OneToMany(mappedBy = "rider", targetEntity = MedicalCard.class)
    private List<MedicalCard> medicalCards;

    @ManyToMany()
    @JoinTable(name = "Rider_Journeys",
            joinColumns = {@JoinColumn(name = "rider_id")},
            inverseJoinColumns = {@JoinColumn(name = "journeys_id")})
    private List<Journey> journeys;

    @JsonBackReference
    @OneToMany(mappedBy = "rider", targetEntity = BikeUsageHistory.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<BikeUsageHistory> bikeUsageHistories;

}
