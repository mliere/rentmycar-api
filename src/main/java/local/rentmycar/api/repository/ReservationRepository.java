package local.rentmycar.api.repository;

import local.rentmycar.api.domain.Renter;
import local.rentmycar.api.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
