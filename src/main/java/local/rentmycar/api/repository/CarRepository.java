package local.rentmycar.api.repository;

import local.rentmycar.api.domain.Car;
import local.rentmycar.api.domain.Owner;
import local.rentmycar.api.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByOwner(Owner owner);
}
