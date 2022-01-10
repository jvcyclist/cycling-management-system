package pl.karas.cyclingmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String referenceNumber;
    private String mark;
    private String model;
    private String type;


    @JsonManagedReference
    @OneToMany(mappedBy = "bike", targetEntity = BikeUsageHistory.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<BikeUsageHistory> bikeUsageHistories;

}
