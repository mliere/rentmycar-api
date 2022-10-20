package local.rentmycar.api.controller.dto;

import local.rentmycar.api.domain.Car;
import local.rentmycar.api.domain.Rating;
import local.rentmycar.api.domain.Renter;
import local.rentmycar.api.domain.Timeslot;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Data
public class ReservationDto {
    private long id;

    @NotEmpty(message = "Car is mandatory")
    private long carId;

    @NotEmpty(message = "Renter is mandatory")
    private long renterId;

    @NotEmpty(message = "Start date is mandatory")
    private Timestamp startDate;

    @NotEmpty(message = "End date is mandatory")
    private Timestamp endDate;

    private int rating;
}
