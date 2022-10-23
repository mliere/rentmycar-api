package local.rentmycar.api.controller;

import local.rentmycar.api.ApiApplication;
import local.rentmycar.api.domain.User;
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
public class UserControllerIntegrationTests {

    @Autowired
    UserService userService;
    @LocalServerPort
    private int port;

    @BeforeEach
    public void beforeEach() {
        // delete all user entries to ensure state is repeatable for each test
        List<User> users = userService.getAll();
        for (User user: users) userService.delete(user.getId());

    }

    final TestRestTemplate restTemplate = new TestRestTemplate();

    final HttpHeaders headers = new HttpHeaders();

    @Test
    public void post_User_ExpectUserDtoResultBody() throws JSONException {
        // arrange
        // set HTTP headers
        headers.add("accept","");
        headers.add("Content-Type","application/json");

        // build JSON body
        JSONObject requestBody = constructPostRequestBody();

        String expected = "testList<Reservation> findByReservationStatus(ReservationStatus reservationStatus);street1";

        // assemble HTTP entity
        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(),
                HttpMethod.POST, entity, String.class
        );

        // assert
        Assert.hasText(expected, response.getBody());
    }

    @Test
    public void post_User_ExpectHttpCreated() throws JSONException {
        // arrange
        // set HTTP headers
        headers.add("accept","");
        headers.add("Content-Type","application/json");

        // build JSON body
        JSONObject requestBody = constructPostRequestBody();

        // assemble HTTP entity
        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(),
                HttpMethod.POST, entity, String.class
        );

        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void post_User_ExpectHttpUnsupportedMedia() throws JSONException {
        // arrange
        // set HTTP headers
        headers.add("accept","");

        // build JSON body
        JSONObject requestBody = constructPostRequestBody();

        // assemble HTTP entity
        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(),
                HttpMethod.POST, entity, String.class
        );

        // assert
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
    }
    @Test
    public void get_AllUsers_ExpectEmptyResultBody() throws JSONException {
        // arrange
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String expected = null;

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(),
                HttpMethod.GET, entity, String.class);

        // assert
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
    @Test
    public void get_AllUsers_ExpectHttpNoContent() {
        // arrange
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(),
                HttpMethod.GET, entity, String.class);

        // assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private static JSONObject constructPostRequestBody() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id",0);
        requestBody.put("role", "owner");
        requestBody.put("firstName", "test");
        requestBody.put("lastName", "user");
        requestBody.put("mailAddress", "test@user.com");
        requestBody.put("address", "teststreet1");
        requestBody.put("country", "testcountry");
        requestBody.put("state", "ex");
        requestBody.put("phoneNumber", "0123456");
        requestBody.put("profilePicture", "string");
        return requestBody;
    }
    private String createURLWithPort() {
        return "http://localhost:" + port + "/users";
    }
}
