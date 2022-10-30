package local.rentmycar.api.controller;

import local.rentmycar.api.controller.dto.ReservationDto;
import local.rentmycar.api.domain.Reservation;
import local.rentmycar.api.domain.Timeslot;
import local.rentmycar.api.service.ReservationServiceInterface;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log
@RestController
@Validated
@RequestMapping("reservations")
public class ReservationController {
    @Autowired
    private ModelMapper modelMapper;

    private final ReservationServiceInterface reservationService;

    @Autowired
    public ReservationController(ReservationServiceInterface reservationService) { this.reservationService = reservationService; }

    @GetMapping()
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        log.info("Received getAll request");
        List<ReservationDto> result = reservationService.getAll()
                .stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDto.class))
                .collect(Collectors.toList());

        if (result.isEmpty()) { return ResponseEntity.noContent().build(); }

        return ResponseEntity.ok(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservationDto> getById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.getById(id);
        return reservation.map(value -> ResponseEntity.ok(modelMapper.map(value, ReservationDto.class)))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<ReservationDto> create(@Valid @RequestBody ReservationDto newReservation) {
        Reservation reservation = reservationService.create(modelMapper.map(newReservation, Timeslot.class),
                newReservation.getCarId(),
                newReservation.getRenterId(),
                newReservation.getRating());

        return new ResponseEntity<>(modelMapper.map(reservation, ReservationDto.class), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        reservationService.delete(id);
        return ResponseEntity.ok().build();
    }
}
