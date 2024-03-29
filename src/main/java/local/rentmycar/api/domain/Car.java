package local.rentmycar.api.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity @Table(name="CAR")
@DynamicUpdate()
@ToString
public class Car extends Vehicle {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="ID")
    @Getter @Setter
    private long id;

    @Column(name="LICENSE_PLATE_NUMBER", unique = true)
    @Getter @Setter
    private String licensePlateNumber;

    @Column(name="PICTURE")
    @Getter @Setter
    private String picture;

    @Column(name="LAT")
    @Getter @Setter
    private String latitude;

    @Column(name="LONG")
    @Getter @Setter
    private String longitude;

    @Column(name="DISTANCE_DRIVEN")
    @Getter @Setter
    private int distanceDriven;

    @Column(name="ROAD_WORTHINESS_EXPIRATION_DATE")
    @Getter @Setter
    private Timestamp roadWorthinessExpirationDate;

    @Column(name="LISTED")
    @Getter @Setter
    private Boolean listed;

    @Column(name="MODEL")
    @Getter @Setter
    private String model;

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

    @Column(name = "PRICE_CAR")
    @Getter @Setter
    private Double price;

    @Column(name = "CURRENT_VALUE")
    @Getter @Setter
    private Double currentValue;

    @Column(name = "TAX_RATE")
    @Getter @Setter
    private int taxRate;

    @Column(name = "INSURANCE")
    @Getter @Setter
    private Double insurance;

    @Column(name = "MILEAGE")
    @Getter @Setter
    private Double mileage;

    @Column(name = "MAINTENANCE")
    @Getter @Setter
    private Double maintenance;
}
