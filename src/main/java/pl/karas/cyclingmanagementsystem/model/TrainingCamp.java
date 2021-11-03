package pl.karas.cyclingmanagementsystem.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class TrainingCamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private Date fromDate;
    private Date toDate;
    @OneToMany
    private Set<Category> categories;
    @OneToMany
    private Set<Training> trainings;
    @OneToOne
    private Journey journey;
}
