package local.rentmycar.api.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
@Data
public class UserDto {

    private long id;

    @Pattern(regexp = "owner|renter", flags = Pattern.Flag.CASE_INSENSITIVE
            , message = "Invalid role, owner and renter are valid")
    private String role;

    private String firstName;
    private String lastName;

    @Email
    private String mailAddress;

    private String address;
    private String country;
    private String state;

    @NotEmpty(message = "Phone number is required")
    private String phoneNumber;

    private String profilePicture;
}