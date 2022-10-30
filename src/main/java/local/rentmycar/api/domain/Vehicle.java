package local.rentmycar.api.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Timestamp;

public abstract class Vehicle {
    @Getter @Setter
    private String type;

    @Getter @Setter
    private String methodOfPropulsion;
}
