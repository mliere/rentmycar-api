package local.rentmycar.api;

import local.rentmycar.api.domain.Car;
import local.rentmycar.api.domain.FuelType;
import local.rentmycar.api.domain.Owner;
import local.rentmycar.api.domain.User;
import local.rentmycar.api.repository.CarRepository;
import local.rentmycar.api.repository.DbInit;
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
    private final FuelTypeRepository fuelTypeRepository;
    public AppReadyEvent(FuelTypeRepository fuelTypeRepository) {
        this.fuelTypeRepository = fuelTypeRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        DbInit init = new DbInit(fuelTypeRepository);

        init.loadFuelTypes();

    }


}
