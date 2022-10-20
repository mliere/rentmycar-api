package local.rentmycar.api.controller;

import local.rentmycar.api.controller.dto.CarDto;
import local.rentmycar.api.controller.dto.UserDto;
import local.rentmycar.api.domain.Car;
import local.rentmycar.api.domain.Owner;
import local.rentmycar.api.domain.User;
import local.rentmycar.api.service.UserService;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log
@Validated
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAll() {
        log.info("Received getAll request");
        List<UserDto> result = userService.getAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());

        if (result.isEmpty()) { return ResponseEntity.noContent().build(); }

        return ResponseEntity.ok(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        Optional<Object> user = Optional.ofNullable(userService.getById(id));
        return user.map(o -> (ResponseEntity<UserDto>) ResponseEntity.ok(modelMapper.map(o, UserDto.class))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto newUser) {

        //log.info("entry of method: " + new Object() {}.getClass().getEnclosingMethod().getName());

            User user = userService.create(modelMapper.map(newUser, User.class));
            return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.CREATED);
        }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @Valid @RequestBody UserDto changedUser) {
        userService.update(id, modelMapper.map(changedUser, User.class));
        return ResponseEntity.ok(changedUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
