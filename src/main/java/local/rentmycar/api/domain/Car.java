package local.rentmycar.api.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="CAR")
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="CAR_ID")
    @Getter @Setter
    private long id;

    @Column(name="LICENSE_PLATE_NUMBER")
    @Getter @Setter
    @NotBlank(message = "License plate number is mandatory")
    private String licensePlateNumber;
}
