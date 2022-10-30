package local.rentmycar.api.service;

import local.rentmycar.api.domain.Reservation;
import local.rentmycar.api.domain.Timeslot;
import local.rentmycar.api.service.Exceptions.MissingResourceException;

import java.util.List;
import java.util.Optional;

public interface ReservationServiceInterface {
    List<Reservation> getAll();
    Boolean existsById(long id);

    Optional<Reservation> getById(long id);

    //Reservation update(long id, Reservation changedReservation);

    Reservation create(Timeslot timeslot, long carId, long renterId, int rating) throws MissingResourceException;

    void delete(long id);
}
