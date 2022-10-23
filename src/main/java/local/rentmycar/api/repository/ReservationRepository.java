package local.rentmycar.api.repository;

import local.rentmycar.api.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/
    List<Reservation> findByCar(Car car);
    List<Reservation> findByReservationStatus(ReservationStatus reservationStatus);
    List<Reservation> findByTimeslot(Timeslot timeslot);

}
