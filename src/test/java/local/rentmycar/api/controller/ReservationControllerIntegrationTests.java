package local.rentmycar.api.controller;

import local.rentmycar.api.ApiApplication;
import local.rentmycar.api.domain.Reservation;
import local.rentmycar.api.service.ReservationService;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ReservationControllerIntegrationTests {

    @Autowired
    ReservationService reservationService;
    @LocalServerPort
    private int port;

    @BeforeEach
    public void beforeEach() {
        // delete all reservation entries to ensure state is repeatable for each test
        List<Reservation> reservations = reservationService.getAll();
        for (Reservation reservation : reservations) reservationService.delete(reservation.getId());

    }

    final TestRestTemplate restTemplate = new TestRestTemplate();

    final HttpHeaders headers = new HttpHeaders();
    @Test
    public void get_AllReservations_ExpectEmptyResultBody() throws JSONException {
        // arrange
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String expected = null;

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(),
                HttpMethod.GET, entity, String.class);

        // assert
        JSONAssert.assertEquals(null, response.getBody(), false);
    }
    @Test
    public void get_AllReservations_ExpectHttpNoContent() {
        // arrange
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(),
                HttpMethod.GET, entity, String.class);

        // assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private String createURLWithPort() {
        return "http://localhost:" + port + "/reservations";
    }

}
