package local.rentmycar.api.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Data
public class CarDto {
    private long id;

    @NotEmpty(message = "distanceDriven is a mandatory field")
    private String distanceDriven;

    private Timestamp roadWorthinessExpirationDate;
    private String model;
    private Boolean listed;
    private Timestamp manufacturingDate;
    private String latitude;
    private String longitude;
    private String picture;

    @NotEmpty(message = "licensePlateNumber is mandatory")
    private String licensePlateNumber;
}
