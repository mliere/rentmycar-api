package local.rentmycar.api.service;

import local.rentmycar.api.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationServiceInterface {
    List<Reservation> getAll();
    Boolean existsById(long id);

    Optional<Reservation> getById(long id);

    Reservation create(Reservation reservation);

    Reservation update(long id, Reservation changedReservation);

    void delete(long id);
}
