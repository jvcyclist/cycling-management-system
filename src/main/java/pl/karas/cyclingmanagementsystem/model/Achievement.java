package pl.karas.cyclingmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String eventRange;
    private String city;
    private String description;
    private String place;
    private Date achievementDate;
    @ManyToOne
    @JoinColumn(name = "rider_id")
    private Rider rider;

}