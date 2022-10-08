package local.rentmycar.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity @Table(name="TIMESLOT")
@ToString
public class Timeslot {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="ID")
    @Getter @Setter
    private long id;

    @Column(name="START_DATE")
    @Getter @Setter
    private Timestamp startDate;

    @Column(name="END_DATE")
    @Getter @Setter
    private Timestamp endDate;
}
