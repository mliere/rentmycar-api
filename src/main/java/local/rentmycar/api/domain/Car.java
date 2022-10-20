package local.rentmycar.api.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Set;

@Entity @Table(name="CAR")
@DynamicUpdate(true)
@ToString
public class Car {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="ID")
    @Getter @Setter
    private long id;

    @Column(name="LICENSE_PLATE_NUMBER", unique = true)
    @Getter @Setter
    @NotBlank(message = "License plate number is mandatory")
    private String licensePlateNumber;

    @Lob @Column(name="PICTURE")
    @Getter @Setter
    private byte[] picture;

    @Column(name="DISTANCE_DRIVEN")
    @Getter @Setter
    private int distanceDriven;

    @Column(name="ROAD_WORTHINESS_EXPIRATION_DATE")
    @Getter @Setter
    private Timestamp roadWorthinessExpirationDate;

    @Column(name="MODEL")
    @Getter @Setter
    private String model;

    @Column(name="LISTED")
    @Getter @Setter
    private Boolean listed;

    @Column(name="MANUFACTURING_DATE")
    @Getter @Setter
    private Timestamp manufacturingDate;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "CAR_FUEL_TYPE",
            joinColumns = @JoinColumn(name = "CAR_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "FUEL_ID", referencedColumnName = "ID"))
    private Set<FuelType> fuelTypeList;

    @ManyToOne//(optional = false)
    @JoinColumn(name = "OWNER_ID", //nullable = false,
            referencedColumnName = "ID")
    @Getter @Setter
    private Owner owner;
}
