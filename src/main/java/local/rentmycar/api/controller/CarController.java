package local.rentmycar.api.controller;

import local.rentmycar.api.dto.CarDto;
import local.rentmycar.api.service.CarService;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Log
@RestController
@Validated
@RequestMapping("cars")
public class CarController {

    @Autowired
    private ModelMapper modelMapper;

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public ResponseEntity<List<CarDto>> getAllCars() {
        log.info("Received getAll request");
        List<CarDto> found = carService.getAllCars()
                .stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .collect(Collectors.toList());

        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(found);
    }

    /*
    @GetMapping("{id}")
    public ResponseEntity<Optional<Car>> getById(@PathVariable Long id) {
        Optional<Car> found = carRepository.findById(id);

        if (found.isPresent()) {
            return ResponseEntity.ok(found);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Car> update(@PathVariable Long id, @Valid @RequestBody Car changedCar) {
        Boolean found = carRepository.existsById(id);

        if (found) {
            carRepository.save(changedCar);
            return ResponseEntity.ok(changedCar);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Car> create(@Valid @RequestBody Car newCar) {
        try {
            Car car = carRepository.save(newCar);
            return new ResponseEntity<>(car, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        if (!carRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        carRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
     */
}
