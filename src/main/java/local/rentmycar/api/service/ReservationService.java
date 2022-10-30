package local.rentmycar.api.service;

import local.rentmycar.api.domain.*;
import local.rentmycar.api.repository.*;
import local.rentmycar.api.service.Exceptions.MissingResourceException;
import local.rentmycar.api.service.Exceptions.TimeslotReservedException;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("CommentedOutCode")
@Log
@Service
public class ReservationService implements ReservationServiceInterface {

    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final RenterRepository renterRepository;
    private final TimeslotRepository timeslotRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ReservationService(
            ReservationRepository reservationRepository
            , CarRepository carRepository
            , RenterRepository renterRepository
            , TimeslotRepository timeslotRepository
            , RatingRepository ratingRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.renterRepository = renterRepository;
        this.timeslotRepository = timeslotRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Boolean existsById(long id) {
        return reservationRepository.existsById(id);
    }

    @Override
    public Optional<Reservation> getById(long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation create(Timeslot timeslot, long carId, long renterId, int rating) throws MissingResourceException {

        Optional<Car> car = carRepository.findById(carId);
        if (car.isEmpty()) {
            throw new MissingResourceException("car", "" + carId);
        }

        Optional<Renter> renter = renterRepository.findById(renterId);
        if (renter.isEmpty()) {
            throw new MissingResourceException("renter", "" + renterId);
        }

        if (!timeslotFree(timeslot, car.get())) {
            throw new TimeslotReservedException();
        }

        Timeslot reservedTimeslot = timeslotRepository.save(timeslot);
        Rating storedRating = ratingRepository.save(new Rating(rating));
        Reservation reservation = new Reservation(0, car.get(), renter.get(),
                reservedTimeslot, storedRating, ReservationStatus.PENDING);

        return reservationRepository.save(reservation);
    }

    /*@Override
    public Reservation update(long id, Reservation changedReservation) {
        return null;
    }*/

    @Override
    public void delete(long id) {
        reservationRepository.deleteById(id);
    }

    private boolean timeslotFree(Timeslot timeslot, Car car) {
        List<Reservation> reservations = reservationRepository.findByCar(car);

        Timestamp s1 = timeslot.getStartDate();
        Timestamp e1 = timeslot.getEndDate();
        for (Reservation reservation : reservations) {

            Timestamp s2 = reservation.getTimeslot().getStartDate();
            Timestamp e2 = reservation.getTimeslot().getEndDate();

            if (s1.before(s2) && e1.after(s2) ||
                    s1.before(e2) && e1.after(e2) ||
                    s1.before(s2) && e1.after(e2) ||
                    s1.after(s2) && e1.before(e2) ||
                    s1.equals(s2) ||
                    e1.equals(e2)) {
                return false;
            }
        }

        return true;
    }
}
