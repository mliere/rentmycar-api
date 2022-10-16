package local.rentmycar.api.controller;

import local.rentmycar.api.controller.dto.CarDto;
import local.rentmycar.api.controller.dto.UserDto;
import local.rentmycar.api.repository.OwnerRepository;
import local.rentmycar.api.service.UserService;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
}
