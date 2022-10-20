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

    @ManyToOne//(optional = false)
    @JoinColumn(name = "CAR_ID", //nullable = false,
            referencedColumnName = "ID")
    private Car car;

    @ManyToOne//(optional = false)
    @JoinColumn(name = "RENTER_ID", //nullable = false,
            referencedColumnName = "ID")
    private Renter renter;

    @OneToOne//(optional = false)
    @JoinColumn(name = "TIMESLOT_ID", //nullable = false,
            referencedColumnName = "ID")
    private Timeslot timeslot;

    @OneToOne @JoinColumn(name = "RESERVATION_ID", referencedColumnName = "ID")
    private Rating rating;
}
