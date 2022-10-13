package local.rentmycar.api.service;

import local.rentmycar.api.domain.Car;
import local.rentmycar.api.domain.User;
import local.rentmycar.api.repository.OwnerRepository;
import local.rentmycar.api.repository.RenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UserService implements UserServiceInterface{
    private final OwnerRepository ownerRepository;
    private final RenterRepository renterRepository;

    @Autowired
    public UserService(OwnerRepository ownerRepository, RenterRepository renterRepository) {
        this.ownerRepository = ownerRepository;
        this.renterRepository = renterRepository;
    }


    @Override
    public List<User> getAll() {
        List<User> joinedList = new ArrayList<>();
        Stream.of(ownerRepository.findAll(), renterRepository.findAll()).forEach(joinedList::addAll);

        return joinedList;
    }

    @Override
    public Optional<User> getById(long id) {
        return Optional.empty();
    }

    @Override
    public User create(User car) {
        return null;
    }

    @Override
    public User update(long id, User changedUser) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
