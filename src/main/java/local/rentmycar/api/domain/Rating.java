package local.rentmycar.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity @Table(name="RATING")
@ToString
public class Rating {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="ID")
    @Getter @Setter
    private long id;

    @Column(name="RATING")
    @Getter @Setter
    private int rating;

    public Rating(int rating) {
        this.rating = rating;
    }
}
