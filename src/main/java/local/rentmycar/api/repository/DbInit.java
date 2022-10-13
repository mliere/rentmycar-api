package local.rentmycar.api.repository;

import local.rentmycar.api.domain.FuelType;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Log
@Component
public class DbInit {
    private final FuelTypeRepository fuelTypeRepository;

    public DbInit(FuelTypeRepository fuelTypeRepository) {
        this.fuelTypeRepository = fuelTypeRepository;
    }

    public void loadFuelTypes() {
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
