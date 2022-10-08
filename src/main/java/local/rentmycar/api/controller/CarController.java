package local.rentmycar.api.controller;

import local.rentmycar.api.domain.Car;
import local.rentmycar.api.repository.CarRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@Log
@RequestMapping("cars")
public class CarController {
    private final CarRepository carRepository;

    @Autowired
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Car>> getAll() {
        log.info("Received getAll request");
        List<Car> found = carRepository.findAll();

        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(found);
    }

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
}
