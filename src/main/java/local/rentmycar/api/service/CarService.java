package local.rentmycar.api.service;

import local.rentmycar.api.domain.Car;
import local.rentmycar.api.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarServiceInterface {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Boolean existsById(long id) {
        return carRepository.existsById(id);
    }

    @Override
    public Optional<Car> getById(long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car create(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car update(long id, Car changedCar) {
        Optional<Car> car = carRepository.findById(id);
        //.orElseThrow(() -> new ResourceNotFoundException("Car", "id", id));

        return carRepository.save(changedCar);
    }
    @Override
    public void delete(long id) {
        if(carRepository.existsById(id)) {
            carRepository.deleteById(id);
        }
    }
}
