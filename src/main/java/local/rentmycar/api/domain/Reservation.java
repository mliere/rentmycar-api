package local.rentmycar.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity @Table(name="RESERVATION")
@ToString
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="ID")
    @Getter @Setter
    private long id;

    @OneToOne(optional = false) @JoinColumn(name = "id", referencedColumnName = "id")
    private Car car;

    @OneToOne(optional = false) @JoinColumn(name = "id", referencedColumnName = "id")
    private Renter renter;

    @OneToOne(optional = false) @JoinColumn(name = "id", referencedColumnName = "id")
    private Timeslot timeslot;

    @OneToOne @JoinColumn(name = "id", referencedColumnName = "id")
    private Rating rating;
}
