package pl.karas.cyclingmanagementsystem.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String refNo;
    private String mark;
    private String model;
    private String type;
    private String isa;
}
