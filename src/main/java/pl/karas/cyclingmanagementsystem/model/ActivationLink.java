package pl.karas.cyclingmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivationLink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String activationToken;
    String activationLink;

    @OneToOne
    User user;

}
