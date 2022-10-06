package local.rentmycar.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="CAR")
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="CAR_ID")
    @Getter
    private long id;

    @Column(name="LICENSE_PLATE_NUMBER")
    @NotEmpty(message = "License plate number is required")
    @Getter @Setter
    private String licensePlateNumber;
}
