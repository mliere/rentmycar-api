package local.rentmycar.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("OWNER")
@ToString
public class Owner extends User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="ID")
    @Getter @Setter
    private long id;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Car> cars;
}
