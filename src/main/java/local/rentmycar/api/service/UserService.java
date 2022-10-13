package local.rentmycar.api.service;

import local.rentmycar.api.repository.CarRepository;
import local.rentmycar.api.repository.OwnerRepository;
import local.rentmycar.api.repository.RenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final OwnerRepository ownerRepository;
    private final RenterRepository renterRepository;

    @Autowired
    public UserService(OwnerRepository ownerRepository, RenterRepository renterRepository) {
        this.ownerRepository = ownerRepository;
        this.renterRepository = renterRepository;
    }


}
