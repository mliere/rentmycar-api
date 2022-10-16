package local.rentmycar.api.service;

import local.rentmycar.api.domain.Owner;
import local.rentmycar.api.domain.Renter;
import local.rentmycar.api.domain.User;
import local.rentmycar.api.repository.OwnerRepository;
import local.rentmycar.api.repository.RenterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UserService implements UserServiceInterface {
    private final OwnerRepository ownerRepository;
    private final RenterRepository renterRepository;

    @Autowired
    private ModelMapper modelMapper;

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

        Optional<Renter> renter = renterRepository.findById(id);
        if (renter.isPresent()) {
            return Optional.of(modelMapper.map(renter, User.class));
        }

        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isPresent()) {
            return Optional.of(modelMapper.map(owner, User.class));
        }
        return Optional.empty();
    }

    @Override
    public User create(User user) {
        switch (user.getClass().getSimpleName()) {
            case "RENTER" -> renterRepository.save((Renter) user);
            case "OWNER" -> ownerRepository.save((Owner) user);
            default -> throw new IllegalArgumentException("Invalid role");
        }
        return user;
    }

    @Override
    public User update(long id, User changedUser) {
        switch (changedUser.getClass().getSimpleName()) {
            case "RENTER":
                return renterRepository.save((Renter) changedUser);
            case "OWNER":
                return ownerRepository.save((Owner) changedUser);
            default:
                throw new IllegalArgumentException("Invalid role");
        }
    }

    @Override
    public void delete(long id) {
        if (ownerRepository.existsById(id)) {
            ownerRepository.deleteById(id);
            return;
        }
        if (renterRepository.existsById(id)) {
            renterRepository.deleteById(id);
        }
    }
}
