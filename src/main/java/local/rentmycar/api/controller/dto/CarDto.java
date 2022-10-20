package local.rentmycar.api.controller.dto;

import lombok.Data;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
public class CarDto {
    private long id;
    @Size(min=2, max=10, message = "Distance driven is mandatory and can't exceed 10 positions.")
    private int distanceDriven;
    private Timestamp roadWorthinessExpirationDate;
    private String model;
    private Boolean listed;
    private Timestamp manufacturingDate;

    @Size(min=2, max=10, message = "License plate number is mandatory")
    private String licensePlateNumber;
}
