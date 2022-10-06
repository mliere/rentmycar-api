package local.rentmycar.api.util;

import local.rentmycar.api.domain.Car;
import local.rentmycar.api.repository.CarRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {

    private final CarRepository carRepository;

    public AppStartupEvent(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Car car = new Car();
        car.setLicensePlateNumber("12345");
        this.carRepository.save(car);
        Iterable<Car> cars = this.carRepository.findAll();
        cars.forEach(System.out::println);

    }
}
