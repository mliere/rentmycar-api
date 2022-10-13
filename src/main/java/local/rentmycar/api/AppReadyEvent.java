package local.rentmycar.api;

import local.rentmycar.api.domain.Car;
import local.rentmycar.api.domain.FuelType;
import local.rentmycar.api.domain.Owner;
import local.rentmycar.api.domain.User;
import local.rentmycar.api.repository.CarRepository;
import local.rentmycar.api.repository.FuelTypeRepository;
import local.rentmycar.api.repository.OwnerRepository;
import lombok.extern.java.Log;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Log
@Component
public class AppReadyEvent implements ApplicationListener<ApplicationReadyEvent> {

    private final CarRepository carRepository;
    private final OwnerRepository ownerRepository;
    private final FuelTypeRepository fuelTypeRepository;
    public AppReadyEvent(CarRepository carRepository,OwnerRepository ownerRepository, FuelTypeRepository fuelTypeRepository) {
        this.carRepository = carRepository;
        this.ownerRepository = ownerRepository;
        this.fuelTypeRepository = fuelTypeRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        loadFuelTypes();
        /*
        Iterable<User> users = ownerRepository.findAll();
        users.forEach(System.out::println);
         */

        /*Owner owner = new Owner();
        owner.setPhoneNumber("123456");
        ownerRepository.save(owner);

        Car car = new Car();
        car.setLicensePlateNumber("1234-aa");
        car.setOwner(owner);
        carRepository.save(car);
         */
    }

    private void loadFuelTypes() {
        if (fuelTypeRepository.count() == 0) {
            List<FuelType> fuelTypes = new ArrayList<>();
            FuelType diesel = new FuelType();
            FuelType benzine = new FuelType();
            FuelType electric = new FuelType();
            FuelType hydrogen = new FuelType();
            FuelType solar = new FuelType();
            diesel.setFuelType("diesel");
            benzine.setFuelType("benzine");
            electric.setFuelType("electric");
            hydrogen.setFuelType("hydrogen");
            solar.setFuelType("solar");

            fuelTypes.add(diesel);
            fuelTypes.add(benzine);
            fuelTypes.add(electric);
            fuelTypes.add(hydrogen);
            fuelTypes.add(solar);

            fuelTypeRepository.saveAll(fuelTypes);
            log.info("Seeded fuel types");
        }
    }
}
