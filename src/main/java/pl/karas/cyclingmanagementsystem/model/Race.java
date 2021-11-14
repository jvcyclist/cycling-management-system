package pl.karas.cyclingmanagementsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Date startDate;
    private Date endDate;
    private String url;
    private String status;
    @OneToOne
    private Journey journey;
    @ManyToMany
    @JoinTable(name = "Race_Category",
            joinColumns = {@JoinColumn(name = "race_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories;
}
