package local.rentmycar.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity @Table(name="FUEL_TYPE")
@ToString
public class FuelType {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="ID")
    @Getter @Setter
    private long id;

    @Column(name="FUEL_TYPE")
    @Getter @Setter
    private String fuelType;

    @ManyToMany(mappedBy = "fuelTypeList")
    @Getter @Setter
    private Set<Car> car;
}
