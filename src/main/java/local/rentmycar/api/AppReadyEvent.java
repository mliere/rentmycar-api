package local.rentmycar.api;

import local.rentmycar.api.domain.User;
import local.rentmycar.api.repository.OwnerRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppReadyEvent implements ApplicationListener<ApplicationReadyEvent> {

    private final OwnerRepository ownerRepository;
    public AppReadyEvent(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        /*
        Iterable<User> users = ownerRepository.findAll();
        users.forEach(System.out::println);
         */
    }
}
