package local.rentmycar.api.service;

import local.rentmycar.api.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    List<User> getAll();

    Optional<User> getById(long id);

    User create(User car);

    User update(long id, User changedUser);

    void delete(long id);
}
