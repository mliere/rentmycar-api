package local.rentmycar.api.service;

import local.rentmycar.api.domain.Car;

import java.util.List;
import java.util.Optional;

public interface CarServiceInterface {
    List<Car> getAll();
    Boolean existsById(long id);

    Optional<Car> getById(long id);

    Car create(Car car);

    Car update(long id, Car changedCar);

    void delete(long id);
}
