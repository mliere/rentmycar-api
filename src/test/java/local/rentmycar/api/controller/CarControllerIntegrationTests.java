package local.rentmycar.api.controller;

import local.rentmycar.api.ApiApplication;
import local.rentmycar.api.domain.Car;
import local.rentmycar.api.service.CarService;
import lombok.extern.java.Log;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CarControllerIntegrationTests {

    @Autowired
    CarService carService;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void beforeEach() {

        // delete all car entries to ensure state is repeatable for each test
        List<Car> cars = carService.getAll();
        for (Car car: cars) carService.delete(car.getId());

    }
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void post_Car_ExpectCarDtoResultBody() throws JSONException {

        // arrange

        // set HTTP headers
        headers.add("accept","");
        headers.add("Content-Type","application/json");

        // build JSON body
        JSONObject requestBody = new JSONObject();
        requestBody.put("id",0);
        requestBody.put("distanceDriven", "12324");
        requestBody.put("roadWorthinessExpirationDate", "2022-10-20T13:08:08.931Z");
        requestBody.put("model", "Opel Vectra");
        requestBody.put("listed", true);
        requestBody.put("manufacturingDate", "2022-10-20T13:08:08.931Z");
        requestBody.put("licensePlateNumber", "123-aV-gg");

        String expected = "123-aV-gg";

        // assemble HTTP entity
        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/cars"),
                HttpMethod.POST, entity, String.class
        );

        // assert
        Assert.hasText(expected, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
    @Test
    public void get_AllCars_ExpectEmptyResultBody() throws JSONException {
        // arrange
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String expected = null;

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/cars"),
                HttpMethod.GET, entity, String.class);

        // assert
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
    @Test
    public void get_AllCars_ExpectHttpNoContent() throws JSONException {
        // arrange
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/cars"),
                HttpMethod.GET, entity, String.class);

        // assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
