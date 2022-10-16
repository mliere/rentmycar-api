package local.rentmycar.api.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserDto {

    @Getter @Setter
    private long id;

    @Pattern(regexp = "owner|renter", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Invalid role, owner and renter are valid")
    @Getter @Setter
    private String role;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    @Email
    @Getter @Setter
    private String mailAddress;

    @Getter @Setter
    private String address;

    @Getter @Setter
    private String country;

    @Getter @Setter
    private String state;

    @NotEmpty(message = "Phone number is required")
    @Getter @Setter
    private String phoneNumber;

    @Getter @Setter
    private String profilePicture;
}
