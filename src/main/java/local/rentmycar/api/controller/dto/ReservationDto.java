package local.rentmycar.api.controller.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class ReservationDto {
    private long id;

    @Min(value = 1, message = "Car is mandatory")
    private long carId;

    @Min(value = 1, message = "Renter is mandatory")
    private long renterId;

    @NotNull(message = "Start date is mandatory")
    private Timestamp startDate;

    @NotNull(message = "End date is mandatory")
    private Timestamp endDate;

    private int rating;
}
