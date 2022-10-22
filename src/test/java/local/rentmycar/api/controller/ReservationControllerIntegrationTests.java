package local.rentmycar.api.controller;

import local.rentmycar.api.ApiApplication;
import local.rentmycar.api.domain.Reservation;
import local.rentmycar.api.domain.User;
import local.rentmycar.api.service.ReservationService;
import local.rentmycar.api.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
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
import org.springframework.util.Assert;

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

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();
    @Test
    public void get_AllReservations_ExpectEmptyResultBody() throws JSONException {
        // arrange
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String expected = null;

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/reservations"),
                HttpMethod.GET, entity, String.class);

        // assert
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
    @Test
    public void get_AllReservations_ExpectHttpNoContent() throws JSONException {
        // arrange
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/reservations"),
                HttpMethod.GET, entity, String.class);

        // assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
