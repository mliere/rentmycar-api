package local.rentmycar.api.dto;

import local.rentmycar.api.domain.FuelType;
import local.rentmycar.api.domain.Owner;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Data
public class CarDto {
    private long id;
    private int distanceDriven;
    private Timestamp roadWorthinessExpirationDate;
    private String model;
    private Boolean listed;
    private Timestamp manufacturingDate;

    @Size(min=2, max=10, message = "License plate number is mandatory")
    private String licensePlateNumber;
}
