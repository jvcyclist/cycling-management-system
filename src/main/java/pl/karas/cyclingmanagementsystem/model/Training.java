package pl.karas.cyclingmanagementsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String type;
    private String kind;
    private Float distance;
    private String description;
    @OneToMany
    private Set<Rider> riders;

}
