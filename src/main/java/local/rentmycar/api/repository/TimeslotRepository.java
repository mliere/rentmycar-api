package local.rentmycar.api.repository;

import local.rentmycar.api.domain.Owner;
import local.rentmycar.api.domain.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeslotRepository extends JpaRepository<Timeslot, Long> {
}
