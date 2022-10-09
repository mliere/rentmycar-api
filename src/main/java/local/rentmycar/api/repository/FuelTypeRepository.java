package local.rentmycar.api.repository;

import local.rentmycar.api.domain.FuelType;
import local.rentmycar.api.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
}
