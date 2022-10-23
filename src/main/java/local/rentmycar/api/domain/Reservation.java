package local.rentmycar.api.domain;

import lombok.*;

import javax.persistence.*;

@Entity @Table(name="RESERVATION")
@ToString @AllArgsConstructor @NoArgsConstructor
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="ID")
    @Getter @Setter
    private long id;

    @ManyToOne//(optional = false)
    @JoinColumn(name = "CAR_ID", //nullable = false,
            referencedColumnName = "ID")
    @Getter @Setter
    private Car car;

    @ManyToOne//(optional = false)
    @JoinColumn(name = "RENTER_ID", //nullable = false,
            referencedColumnName = "ID")
    @Getter @Setter
    private Renter renter;

    @OneToOne//(optional = false)
    @JoinColumn(name = "TIMESLOT_ID", //nullable = false,
            referencedColumnName = "ID")
    @Getter @Setter
    private Timeslot timeslot;

    @OneToOne @JoinColumn(name = "RESERVATION_ID", referencedColumnName = "ID")
    @Getter @Setter
    private Rating rating;

    @Column(name="RESERVATION_STATUS")
    @Getter @Setter
    private ReservationStatus reservationStatus;
}
