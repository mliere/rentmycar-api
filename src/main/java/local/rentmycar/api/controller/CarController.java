package local.rentmycar.api.controller;

import local.rentmycar.api.domain.Car;
import local.rentmycar.api.controller.dto.CarDto;
import local.rentmycar.api.service.CarService;
import local.rentmycar.api.service.CarServiceInterface;
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
@RequestMapping("cars")
public class CarController {

    @Autowired
    private ModelMapper modelMapper;

    private final CarServiceInterface carService;

    @Autowired
    public CarController(CarServiceInterface carService) {
        this.carService = carService;
    }

    @GetMapping()
    public ResponseEntity<List<CarDto>> getAllCars() {
        log.info("Received getAll request");
        List<CarDto> result = carService.getAll()
                .stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .collect(Collectors.toList());

        if (result.isEmpty()) { return ResponseEntity.noContent().build(); }

        return ResponseEntity.ok(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<CarDto> getById(@PathVariable Long id) {
        Optional<Car> car = carService.getById(id);
        return car.map(value -> (ResponseEntity<CarDto>) ResponseEntity.ok(modelMapper.map(value, CarDto.class)))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<CarDto> update(@PathVariable Long id, @Valid @RequestBody CarDto changedCar) {
            carService.update(id, modelMapper.map(changedCar, Car.class));
            return ResponseEntity.ok(changedCar);
    }

    @PostMapping
    public ResponseEntity<CarDto> create(@Valid @RequestBody CarDto newCar) {
        try {
            Car car = carService.create(modelMapper.map(newCar, Car.class));
            return new ResponseEntity<>(modelMapper.map(car, CarDto.class), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.ok().build();
    }

}