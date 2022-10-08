package local.rentmycar.api;

import local.rentmycar.api.domain.Car;
import local.rentmycar.api.domain.User;
import local.rentmycar.api.repository.CarRepository;
import local.rentmycar.api.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppReadyEvent implements ApplicationListener<ApplicationReadyEvent> {

    public AppReadyEvent() {
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
    }
}
