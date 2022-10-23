package local.rentmycar.api.service;

import local.rentmycar.api.controller.dto.ReservationDto;
import local.rentmycar.api.domain.*;
import local.rentmycar.api.repository.*;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            , RatingRepository ratingRepository)
    {
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
    public Reservation create(ReservationDto reservationDto) {
        Optional<Car> car = carRepository.findById(reservationDto.getCarId());
        Optional<Renter> renter = renterRepository.findById(reservationDto.getRenterId());
        Timeslot timeslot = timeslotRepository.save(modelMapper.map(reservationDto, Timeslot.class));
        Rating rating = ratingRepository.save(new Rating(reservationDto.getRating()));

        Reservation reservation = new Reservation(0,car.get(), renter.get(),timeslot,rating,ReservationStatus.PENDING);

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
}
