package local.rentmycar.api.service;

import local.rentmycar.api.domain.Car;
import local.rentmycar.api.domain.Owner;
import local.rentmycar.api.repository.CarRepository;
import local.rentmycar.api.repository.OwnerRepository;
import local.rentmycar.api.service.Exceptions.MissingResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarServiceInterface {
    private final CarRepository carRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public CarService(CarRepository carRepository, OwnerRepository ownerRepository) {
        this.carRepository = carRepository;
        this.ownerRepository = ownerRepository;
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
    public List<Car> getByOwner(long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) {
            throw new MissingResourceException("owner", "" + id);
        }
        return carRepository.findByOwner(owner.get());
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
