package local.rentmycar.api;

import local.rentmycar.api.domain.Car;
import local.rentmycar.api.domain.Owner;
import local.rentmycar.api.domain.User;
import local.rentmycar.api.repository.CarRepository;
import local.rentmycar.api.repository.OwnerRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppReadyEvent implements ApplicationListener<ApplicationReadyEvent> {

    private final CarRepository carRepository;
    private final OwnerRepository ownerRepository;
    public AppReadyEvent(CarRepository carRepository,OwnerRepository ownerRepository) {
        this.carRepository = carRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        /*
        Iterable<User> users = ownerRepository.findAll();
        users.forEach(System.out::println);
         */

        Owner owner = new Owner();
        owner.setPhoneNumber("123456");
        ownerRepository.save(owner);

        Car car = new Car();
        car.setLicensePlateNumber("1234-aa");
        car.setOwner(owner);
        carRepository.save(car);
    }
}
