package local.rentmycar.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="API_USER")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USER_ID")
    @Getter
    private long id;

    @Column(name="FIRST_NAME")
    @Getter @Setter
    private String firstName;

    @Column(name="LAST_NAME")
    @Getter @Setter
    private String lastName;

    @Column(name="EMAIL_ADDRESS")
    @Email
    @Getter @Setter
    private String mailAddress;

    @Column(name="ADDRESS")
    @Getter @Setter
    private String address;

    @Column(name="COUNTRY")
    @Getter @Setter
    private String country;

    @Column(name="STATE")
    @Getter @Setter
    private String state;

    @Column(name="PHONE_NUMBER")
    @NotEmpty(message = "Phone number is required")
    @Getter @Setter
    private String phoneNumber;
}
