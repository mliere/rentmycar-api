package local.rentmycar.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("RENTER")
@ToString
public class Renter extends User{
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="ID")
    @Getter @Setter
    private long id;
}
