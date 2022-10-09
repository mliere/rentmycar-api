package local.rentmycar.api.service;

import local.rentmycar.api.domain.Car;
import local.rentmycar.api.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(long id) {
        return carRepository.findById(id);
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(long id, Car changedCar) {
        Optional<Car> car = carRepository.findById(id);
        //.orElseThrow(() -> new ResourceNotFoundException("Car", "id", id));

        return carRepository.save(changedCar);
    }
    public void deleteCar(long id) {
        if(carRepository.existsById(id)) {
            carRepository.deleteById(id);
        }
    }
}
