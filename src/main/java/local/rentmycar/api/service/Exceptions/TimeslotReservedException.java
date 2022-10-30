package local.rentmycar.api.service.Exceptions;

public class TimeslotReservedException extends RuntimeException {

    public TimeslotReservedException() {
        super("Requested timeslot unavailable.");
    }
}
