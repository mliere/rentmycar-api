package local.rentmycar.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
    @Getter @Setter
    private String licensePlateNumber;
}
