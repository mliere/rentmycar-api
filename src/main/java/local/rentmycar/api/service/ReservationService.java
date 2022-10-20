package local.rentmycar.api.service;

import local.rentmycar.api.domain.Reservation;
import local.rentmycar.api.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements ReservationServiceInterface {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> getAll() { return reservationRepository.findAll(); }

    @Override
    public Boolean existsById(long id) {
        return null;
    }

    @Override
    public Optional<Reservation> getById(long id) {
        return Optional.empty();
    }

    @Override
    public Reservation create(Reservation reservation) {
        return null;
    }

    @Override
    public Reservation update(long id, Reservation changedReservation) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
