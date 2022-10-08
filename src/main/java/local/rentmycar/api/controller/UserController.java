package local.rentmycar.api.controller;

import local.rentmycar.api.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private final OwnerRepository ownerRepository;

    @Autowired
    public UserController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }
}
