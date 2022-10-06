package local.rentmycar.api.repository;

import local.rentmycar.api.domain.Car;
import local.rentmycar.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
